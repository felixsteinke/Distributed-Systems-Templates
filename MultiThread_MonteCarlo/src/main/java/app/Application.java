package app;

import java.util.concurrent.ExecutionException;

public class Application {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        PiGenerator generator = new PiGenerator();

        long startTime = System.nanoTime();
        System.out.println("Single thread result: " + generator.generatePI(100_000_000));
        long endTime = System.nanoTime();
        System.out.println("Single thread time: " + (endTime - startTime) / 1_000_000_000 + " seconds");

        PiGeneratorMultiThread generatorThread = new PiGeneratorMultiThread(2);

        startTime = System.nanoTime();
        System.out.println("Multi thread result: " + generatorThread.generatePi(100_000_000));
        endTime = System.nanoTime();
        System.out.println("Multi thread time: " + (endTime - startTime) / 1_000_000_000 + " seconds");
    }
}
