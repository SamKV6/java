import java.util.Comparator;
import java.util.PriorityQueue;

// Класс автомобиль
class Car {
    private String model;
    private Priority priority;
    private long arrivalOrder; // для учета порядка поступления
    
    public enum Priority {
        ORDINARY(1, "Обычный"),
        POLICE(2, "Полиция"),
        MEDICAL(3, "Медицинский");
        
        private int level;
        private String description;
        
        Priority(int level, String description) {
            this.level = level;
            this.description = description;
        }
        
        public int getLevel() {
            return level;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    // Конструктор без учета порядка прибытия
    public Car(String model, Priority priority) {
        this.model = model;
        this.priority = priority;
        this.arrivalOrder = 0;
    }
    
    // Конструктор с учетом порядка прибытия (для дополнительного задания)
    public Car(String model, Priority priority, long arrivalOrder) {
        this.model = model;
        this.priority = priority;
        this.arrivalOrder = arrivalOrder;
    }
    
    public String getModel() {
        return model;
    }
    
    public Priority getPriority() {
        return priority;
    }
    
    public long getArrivalOrder() {
        return arrivalOrder;
    }
    
    @Override
    public String toString() {
        if (arrivalOrder > 0) {
            return String.format("%s [%s, приоритет: %d, порядок: %d]", 
                    model, priority.getDescription(), priority.getLevel(), arrivalOrder);
        } else {
            return String.format("%s [%s, приоритет: %d]", 
                    model, priority.getDescription(), priority.getLevel());
        }
    }
}

// Компаратор, где высший приоритет у медицинских автомобилей
class MedicalPriorityComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        // Медицинский (3) > Полиция (2) > Обычный (1)
        // Чем больше уровень, тем выше приоритет
        return Integer.compare(c2.getPriority().getLevel(), c1.getPriority().getLevel());
    }
    
    @Override
    public String toString() {
        return "Медицинский приоритет (медицина > полиция > обычный)";
    }
}

// Компаратор, где высший приоритет у полиции
class PolicePriorityComparator implements Comparator<Car> {
    
    // Вспомогательный метод для переназначения приоритетов
    private int getMappedPriorityLevel(Car car) {
        switch (car.getPriority()) {
            case POLICE: return 3;  // Полиция - наивысший
            case MEDICAL: return 2;  // Медицинский - средний
            case ORDINARY: return 1; // Обычный - низший
            default: return 0;
        }
    }
    
    @Override
    public int compare(Car c1, Car c2) {
        // Переназначаем приоритеты: полиция становится самым высоким
        return Integer.compare(getMappedPriorityLevel(c2), getMappedPriorityLevel(c1));
    }
    
    @Override
    public String toString() {
        return "Полицейский приоритет (полиция > медицина > обычный)";
    }
}

// ДОПОЛНИТЕЛЬНОЕ ЗАДАНИЕ: Компаратор, учитывающий порядок поступления
class FairPriorityComparator implements Comparator<Car> {
    private Comparator<Car> priorityComparator;
    
    public FairPriorityComparator(Comparator<Car> priorityComparator) {
        this.priorityComparator = priorityComparator;
    }
    
    @Override
    public int compare(Car c1, Car c2) {
        // Сначала сравниваем по приоритету
        int priorityCompare = priorityComparator.compare(c1, c2);
        
        // Если приоритеты одинаковые, сравниваем по порядку прибытия
        if (priorityCompare == 0) {
            return Long.compare(c1.getArrivalOrder(), c2.getArrivalOrder());
        }
        
        return priorityCompare;
    }
    
    @Override
    public String toString() {
        return "Справедливый приоритет (с учетом порядка прибытия)";
    }
}

// Класс-дженерик PriorityQueue для заправки
class GasStation<T extends Car> {
    private PriorityQueue<T> queue;
    private String stationName;
    private Comparator<T> comparator;
    
    public GasStation(String stationName, Comparator<T> comparator) {
        this.stationName = stationName;
        this.comparator = comparator;
        this.queue = new PriorityQueue<>(comparator);
    }
    
    public void addCar(T car) {
        queue.offer(car);
        System.out.println("  Добавлен: " + car);
    }
    
    public void refuelCar() {
        T car = queue.poll();
        if (car != null) {
            System.out.println("  Заправлен: " + car);
        }
    }
    
    public void refuelAll() {
        System.out.println("\n--- Процесс заправки на \"" + stationName + "\" ---");
        System.out.println("  Компаратор: " + comparator);
        System.out.println("  Очередь машин:");
        
        int count = 1;
        while (!queue.isEmpty()) {
            System.out.print("  " + count++ + ". ");
            refuelCar();
        }
        
        if (count == 1) {
            System.out.println("  Очередь пуста!");
        }
    }
    
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    public int size() {
        return queue.size();
    }
}