import java.util.concurrent.atomic.AtomicInteger;

public class AssemblyLine {
    private final BlockingQueue<PartC> queueC;
    private final BlockingQueue<PartD> queueD;
    private final BlockingQueue<PartA> queueA;
    private final BlockingQueue<PartB> queueB;
    private final BlockingQueue<Product> queueProduct;
    
    private final AtomicInteger partCCounter = new AtomicInteger(0);
    private final AtomicInteger partDCounter = new AtomicInteger(0);
    private final AtomicInteger partACounter = new AtomicInteger(0);
    private final AtomicInteger partBCounter = new AtomicInteger(0);
    private final AtomicInteger productCounter = new AtomicInteger(0);
    
    private volatile boolean running = true;
    
    public AssemblyLine() {
        queueC = new BlockingQueue<>(5);
        queueD = new BlockingQueue<>(5);
        queueA = new BlockingQueue<>(5);
        queueB = new BlockingQueue<>(5);
        queueProduct = new BlockingQueue<>(5);
    }
    
    public void start() {
        // Поток для сборки детали C (2 сек)
        Thread threadC = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(2000);
                    PartC partC = new PartC(partCCounter.incrementAndGet());
                    queueC.put(partC);
                    printQueueStatus();
                    System.out.println("Произведена деталь C: " + partC);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        // Поток для сборки детали D (1 сек)
        Thread threadD = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(1000);
                    PartD partD = new PartD(partDCounter.incrementAndGet());
                    queueD.put(partD);
                    printQueueStatus();
                    System.out.println("Произведена деталь D: " + partD);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        // Поток для сборки детали B из C и D (3 сек)
        Thread threadB = new Thread(() -> {
            while (running) {
                try {
                    PartC partC = queueC.take();
                    PartD partD = queueD.take();
                    Thread.sleep(3000);
                    PartB partB = new PartB(partBCounter.incrementAndGet(), partC, partD);
                    queueB.put(partB);
                    printQueueStatus();
                    System.out.println("Произведена деталь B: " + partB);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        // Поток для сборки детали A (1 сек)
        Thread threadA = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(1000);
                    PartA partA = new PartA(partACounter.incrementAndGet());
                    queueA.put(partA);
                    printQueueStatus();
                    System.out.println("Произведена деталь A: " + partA);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        // Поток для сборки готового продукта из A и B (2 сек)
        Thread productThread = new Thread(() -> {
            while (running) {
                try {
                    PartA partA = queueA.take();
                    PartB partB = queueB.take();
                    Thread.sleep(2000);
                    Product product = new Product(productCounter.incrementAndGet());
                    queueProduct.put(product);
                    printQueueStatus();
                    System.out.println("=== ПРОИЗВЕДЕН ПРОДУКТ: " + product + " ===");
                    System.out.println("Использованы детали: " + partA + " и " + partB);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        threadC.start();
        threadD.start();
        threadB.start();
        threadA.start();
        productThread.start();
    }
    
    private void printQueueStatus() {
        System.out.printf("Статус очередей - A: %d, B: %d, C: %d, D: %d, Продукты: %d%n",
            queueA.size(), queueB.size(), queueC.size(), queueD.size(), queueProduct.size());
    }
    
    public void stop() {
        running = false;
    }
    
    public static void main(String[] args) {
        AssemblyLine assemblyLine = new AssemblyLine();
        assemblyLine.start();
        
        // Работаем 30 секунд для демонстрации
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assemblyLine.stop();
        System.out.println("Производство остановлено");
    }
}