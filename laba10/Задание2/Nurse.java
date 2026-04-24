public class Nurse implements Runnable {
    private final int id;
    private final BlockingPatientQueue queue;
    private volatile boolean running = true;
    private int patientsServed = 0;
    
    public Nurse(int id, BlockingPatientQueue queue) {
        this.id = id;
        this.queue = queue;
    }
    
    @Override
    public void run() {
        while (running) {
            try {
                Patient patient = queue.take();
                if (patient == null) {
                    break;
                }
                
                System.out.printf("Медсестра %d начала обслуживание %s%n", 
                    id, patient.getName());
                
                Thread.sleep(patient.getServiceTime());
                
                patientsServed++;
                System.out.printf("Медсестра %d закончила обслуживание %s. " +
                    "Время обслуживания: %d мс. Обслужено пациентов: %d%n", 
                    id, patient.getName(), patient.getServiceTime(), patientsServed);
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.printf("Медсестра %d завершила работу. Всего обслужено: %d пациентов%n", 
            id, patientsServed);
    }
    
    public void stop() {
        running = false;
    }
}