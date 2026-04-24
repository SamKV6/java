import java.util.ArrayList;
import java.util.List;

public class MedicalClinic {
    private final BlockingPatientQueue queue;
    private final List<Nurse> nurses;
    private final List<Thread> nurseThreads;
    private final int patientCount;
    
    public MedicalClinic(int nurseCount, int patientCount) {
        this.queue = new BlockingPatientQueue();
        this.nurses = new ArrayList<>();
        this.nurseThreads = new ArrayList<>();
        this.patientCount = patientCount;
        
        for (int i = 1; i <= nurseCount; i++) {
            Nurse nurse = new Nurse(i, queue);
            nurses.add(nurse);
            nurseThreads.add(new Thread(nurse, "Nurse-" + i));
        }
    }
    
    public void start() {
        // Запускаем медсестер
        for (Thread thread : nurseThreads) {
            thread.start();
        }
        
        // Создаем и добавляем пациентов в очередь
        Thread patientGenerator = new Thread(() -> {
            for (int i = 1; i <= patientCount; i++) {
                try {
                    Patient patient = new Patient();
                    queue.put(patient);
                    System.out.println("Добавлен в очередь: " + patient);
                    Thread.sleep(500); // Небольшая задержка между добавлением пациентов
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            queue.finish();
            System.out.println("Все пациенты добавлены в очередь");
        });
        
        patientGenerator.start();
        
        // Ждем завершения генератора пациентов
        try {
            patientGenerator.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Ждем завершения всех медсестер
        for (Thread thread : nurseThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("Все пациенты обслужены. Работа завершена.");
    }
    
    public static void main(String[] args) {
        MedicalClinic clinic = new MedicalClinic(3, 10);
        clinic.start();
    }
}