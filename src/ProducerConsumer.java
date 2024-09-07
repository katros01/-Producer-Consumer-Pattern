import java.util.LinkedList;
import java.util.List;

public class ProducerConsumer {
    private static final int CAPACITY = 5;
    private final List<Integer> buffer = new LinkedList<>();
    private volatile boolean isRunning;

    public ProducerConsumer(boolean isRunning) {
        this.isRunning = isRunning;
    }
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (buffer.size() == CAPACITY) {
                    wait();
                }
                buffer.add(value++);
                System.out.println("Produced: " + value);
                notify();
                Thread.sleep(1000);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    wait();
                }
                int value = buffer.remove(0);
                System.out.println("Consumed: " + value);
                notify(); // Notify producer to produce
                Thread.sleep(1000); // Simulate time delay
            }
        }
    }

    public void stop() {
        isRunning = false;
    }

    public void waitNotifyDemostration () {

        Thread producerThread = new Thread(() -> {
            try {
                produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stop();
        producerThread.interrupt();
        consumerThread.interrupt();
    }
}

