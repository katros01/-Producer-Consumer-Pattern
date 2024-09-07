import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ProducerConsumerBlockingQueue {
    private static final int CAPACITY = 5;
    private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(CAPACITY);
    private volatile boolean isRunning;

    public ProducerConsumerBlockingQueue(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            queue.put(value++);
            System.out.println("Produced: " + value);
            Thread.sleep(1000);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            int value = queue.take();
            System.out.println("Consumed: " + value);
            Thread.sleep(1000);
        }
    }

    public void stop() {
        isRunning = false;
    }

    public void blockingQueueDemostration() {

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

