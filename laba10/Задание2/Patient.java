import java.util.Random;

public class Patient {
    private static int counter = 0;
    private final int id;
    private final int serviceTime;
    private final String name;
    
    public Patient() {
        this.id = ++counter;
        Random random = new Random();
        this.serviceTime = random.nextInt(5000) + 1000; // от 1 до 5 секунд
        this.name = "Пациент №" + id;
    }
    
    public Patient(String name) {
        this.id = ++counter;
        Random random = new Random();
        this.serviceTime = random.nextInt(5000) + 1000;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public int getServiceTime() {
        return serviceTime;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name + " (время обслуживания: " + serviceTime + "мс)";
    }
}