import java.util.PriorityQueue;

public class main2 {
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  МОДЕЛИРОВАНИЕ ПРИОРИТЕТНОЙ ОЧЕРЕДИ");
        System.out.println("           НА АВТОЗАПРАВКЕ");
        System.out.println("===========================================\n");
        
        // ЧАСТЬ 1: Медицинский приоритет
        System.out.println("ЧАСТЬ 1. МЕДИЦИНСКИЙ ПРИОРИТЕТ");
        System.out.println("-------------------------------------------");
        
        GasStation<Car> medicalStation = new GasStation<>(
            "Медицинская АЗС", 
            new MedicalPriorityComparator()
        );
        
        // Добавляем машины в произвольном порядке
        medicalStation.addCar(new Car("Toyota Camry", Car.Priority.ORDINARY));
        medicalStation.addCar(new Car("Skoda Octavia", Car.Priority.POLICE));
        medicalStation.addCar(new Car("Renault Logan", Car.Priority.ORDINARY));
        medicalStation.addCar(new Car("Ambulance Mercedes", Car.Priority.MEDICAL));
        medicalStation.addCar(new Car("Ford Focus", Car.Priority.ORDINARY));
        medicalStation.addCar(new Car("Police BMW", Car.Priority.POLICE));
        medicalStation.addCar(new Car("Medical Gazelle", Car.Priority.MEDICAL));
        
        medicalStation.refuelAll();
        
        System.out.println("\n");
        
        // ЧАСТЬ 2: Полицейский приоритет
        System.out.println("ЧАСТЬ 2. ПОЛИЦЕЙСКИЙ ПРИОРИТЕТ");
        System.out.println("-------------------------------------------");
        
        GasStation<Car> policeStation = new GasStation<>(
            "Полицейская АЗС", 
            new PolicePriorityComparator()
        );
        
        // Добавляем те же машины
        policeStation.addCar(new Car("Toyota Camry", Car.Priority.ORDINARY));
        policeStation.addCar(new Car("Skoda Octavia", Car.Priority.POLICE));
        policeStation.addCar(new Car("Renault Logan", Car.Priority.ORDINARY));
        policeStation.addCar(new Car("Ambulance Mercedes", Car.Priority.MEDICAL));
        policeStation.addCar(new Car("Ford Focus", Car.Priority.ORDINARY));
        policeStation.addCar(new Car("Police BMW", Car.Priority.POLICE));
        policeStation.addCar(new Car("Medical Gazelle", Car.Priority.MEDICAL));
        
        policeStation.refuelAll();
        
        System.out.println("\n");
        
        // ЧАСТЬ 3: ДОПОЛНИТЕЛЬНОЕ ЗАДАНИЕ - Учет порядка прибытия
        System.out.println("ЧАСТЬ 3. ДОПОЛНИТЕЛЬНОЕ ЗАДАНИЕ");
        System.out.println("-------------------------------------------");
        System.out.println("Сравнение стандартного поведения и учета порядка прибытия\n");
        
        // 3.1 Стандартное поведение (без учета порядка)
        System.out.println("--- Случай 3.1: Стандартное поведение ---");
        System.out.println("    (порядок внутри приоритета может быть любым)");
        
        PriorityQueue<Car> standardQueue = new PriorityQueue<>(new MedicalPriorityComparator());
        
        standardQueue.offer(new Car("Toyota 1", Car.Priority.ORDINARY));
        standardQueue.offer(new Car("Toyota 2", Car.Priority.ORDINARY));
        standardQueue.offer(new Car("Toyota 3", Car.Priority.ORDINARY));
        standardQueue.offer(new Car("Police A", Car.Priority.POLICE));
        standardQueue.offer(new Car("Police B", Car.Priority.POLICE));
        standardQueue.offer(new Car("Ambulance X", Car.Priority.MEDICAL));
        
        System.out.println("  Очередь добавления: [Toyota1, Toyota2, Toyota3, PoliceA, PoliceB, AmbulanceX]");
        System.out.println("  Результат заправки:");
        
        int num = 1;
        while (!standardQueue.isEmpty()) {
            System.out.println("    " + num++ + ". Заправлен: " + standardQueue.poll());
        }
        
        System.out.println();
        
        // 3.2 С учетом порядка прибытия (справедливая очередь)
        System.out.println("--- Случай 3.2: С учетом порядка прибытия ---");
        System.out.println("    (сохраняется порядок внутри одного приоритета)");
        
        FairPriorityComparator fairComparator = new FairPriorityComparator(new MedicalPriorityComparator());
        PriorityQueue<Car> fairQueue = new PriorityQueue<>(fairComparator);
        
        // Добавляем с указанием порядка прибытия
        long order = 1;
        fairQueue.offer(new Car("Toyota 1", Car.Priority.ORDINARY, order++));
        fairQueue.offer(new Car("Toyota 2", Car.Priority.ORDINARY, order++));
        fairQueue.offer(new Car("Toyota 3", Car.Priority.ORDINARY, order++));
        fairQueue.offer(new Car("Police A", Car.Priority.POLICE, order++));
        fairQueue.offer(new Car("Police B", Car.Priority.POLICE, order++));
        fairQueue.offer(new Car("Ambulance X", Car.Priority.MEDICAL, order++));
        
        System.out.println("  Очередь добавления: [Toyota1, Toyota2, Toyota3, PoliceA, PoliceB, AmbulanceX]");
        System.out.println("  Результат заправки:");
        
        num = 1;
        while (!fairQueue.isEmpty()) {
            System.out.println("    " + num++ + ". Заправлен: " + fairQueue.poll());
        }
        
        System.out.println("\n");
        
        // ЧАСТЬ 4: Демонстрация важности учета порядка
        System.out.println("ЧАСТЬ 4. ДЕМОНСТРАЦИЯ РАЗНИЦЫ");
        System.out.println("-------------------------------------------");
        System.out.println("Ситуация: Несколько машин с одинаковым приоритетом\n");
        
        // Медицинский приоритет без учета порядка
        System.out.println("  Медицинский приоритет (обычный компаратор):");
        PriorityQueue<Car> medQueue1 = new PriorityQueue<>(new MedicalPriorityComparator());
        medQueue1.offer(new Car("Ambulance 1 (прибыла первой)", Car.Priority.MEDICAL));
        medQueue1.offer(new Car("Ambulance 2 (прибыла второй)", Car.Priority.MEDICAL));
        medQueue1.offer(new Car("Ambulance 3 (прибыла третьей)", Car.Priority.MEDICAL));
        
        System.out.print("    Результат: ");
        while (!medQueue1.isEmpty()) {
            System.out.print("[" + medQueue1.poll().getModel() + "] ");
        }
        System.out.println(" (порядок может меняться при разных запусках)");
        
        // Медицинский приоритет с учетом порядка
        System.out.println("\n  Медицинский приоритет (с учетом порядка прибытия):");
        FairPriorityComparator fairMedComparator = new FairPriorityComparator(new MedicalPriorityComparator());
        PriorityQueue<Car> medQueue2 = new PriorityQueue<>(fairMedComparator);
        
        long orderMed = 1;
        medQueue2.offer(new Car("Ambulance 1 (прибыла первой)", Car.Priority.MEDICAL, orderMed++));
        medQueue2.offer(new Car("Ambulance 2 (прибыла второй)", Car.Priority.MEDICAL, orderMed++));
        medQueue2.offer(new Car("Ambulance 3 (прибыла третьей)", Car.Priority.MEDICAL, orderMed++));
        
        System.out.print("    Результат: ");
        while (!medQueue2.isEmpty()) {
            System.out.print("[" + medQueue2.poll().getModel() + "] ");
        }
        System.out.println(" (порядок строго соответствует прибытию)");
        
        System.out.println("\n===========================================");
        System.out.println("  ВЫВОДЫ:");
        System.out.println("  • PriorityQueue позволяет гибко настраивать приоритеты");
        System.out.println("  • Медицинский и полицейский приоритеты легко переключаются");
        System.out.println("  • Дополнительное задание: для учета порядка прибытия");
        System.out.println("    необходимо использовать составной компаратор");
        System.out.println("===========================================");
    }
}