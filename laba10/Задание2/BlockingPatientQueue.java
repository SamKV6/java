import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingPatientQueue {
    private final Queue<Patient> queue = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private boolean finished = false;
    
    public void put(Patient patient) throws InterruptedException {
        lock.lock();
        try {
            queue.offer(patient);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public Patient take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty() && !finished) {
                notEmpty.await();
            }
            if (queue.isEmpty() && finished) {
                return null;
            }
            return queue.poll();
        } finally {
            lock.unlock();
        }
    }
    
    public void finish() {
        lock.lock();
        try {
            finished = true;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }
    
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}