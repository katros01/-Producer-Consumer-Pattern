public class Benchmark {

    public static void benchmarkProducerConsumer(Runnable producerConsumerMethod) throws InterruptedException {

        long startTime = System.nanoTime();

        producerConsumerMethod.run();

        long endTime = System.nanoTime();

        long durationInMillis = (endTime - startTime) / 1_000_000;
        System.out.println("Time taken: " + durationInMillis + " ms");
    }
}

