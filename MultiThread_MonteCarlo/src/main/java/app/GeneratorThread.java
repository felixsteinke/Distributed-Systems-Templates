package app;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GeneratorThread {

    private final int threads;
    private final ExecutorService service;
    private final ArrayList<Double> partialResults = new ArrayList<>();

    public GeneratorThread(int threads) {
        this.threads = threads;
        this.service = Executors.newFixedThreadPool(threads);
    }

    private void generatePartialResults(double totalCount) {
        double partialCount = totalCount / threads;
        for (int i = 0; i < threads; i++) {
            commitJob(partialCount);
        }
    }

    private void commitJob(double partialCount) {
        service.submit(() -> {
            Generator generator = new Generator();
            savePartialResult(generator.generatePI(partialCount));
        });
    }

    private synchronized void savePartialResult(double partialResult) {
        partialResults.add(partialResult);
    }

    public double generatePi(double totalCount) {
        double pi = 0;
        generatePartialResults(totalCount);
        while (true) {
            if (partialResults.size() == threads) {
                for (double partialResult : partialResults) {
                    pi += partialResult;
                }
                return pi / threads;
            }
        }

    }

}
