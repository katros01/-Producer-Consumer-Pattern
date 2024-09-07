import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        boolean isRunning = true;
        ProducerConsumer basicProducerConsumer = new ProducerConsumer(isRunning);
        ProducerConsumerBlockingQueue blockingQueue = new ProducerConsumerBlockingQueue(isRunning);
        Benchmark benchmark = new Benchmark();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Using notify() and wait() method");
        basicProducerConsumer.waitNotifyDemostration();

        System.out.println("\nPress Enter to continue to the BlockingQueue demonstration...");
        scanner.nextLine();

        System.out.println("Using BlockingQueue");
        blockingQueue.blockingQueueDemostration();

        scanner.close();

        // Benchmark wait/notify implementation
        System.out.println("\nBenchmarking wait/notify implementation:");
        Benchmark.benchmarkProducerConsumer(basicProducerConsumer::waitNotifyDemostration);

        // Benchmark BlockingQueue implementation
        System.out.println("\nBenchmarking BlockingQueue implementation:");
        Benchmark.benchmarkProducerConsumer(blockingQueue::blockingQueueDemostration);
    }
}