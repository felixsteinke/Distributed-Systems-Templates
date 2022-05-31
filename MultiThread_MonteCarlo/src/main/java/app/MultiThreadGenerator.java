package app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class MultiThreadGenerator {
    private static final Logger logger = Logger.getLogger(MultiThreadGenerator.class.getName());
    private final int threads;
    private CompletionService<Double> service;


    public MultiThreadGenerator(int threads) {
        this.threads = threads;
    }

    public double generatePi(double totalCount) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        service = new ExecutorCompletionService<>(executor);
        submitMultiThreadGenerator(totalCount, threads);
        double pi = 0;
        for (double partialResult : joinMultiThreadResult(threads)) {
            pi += partialResult;
        }
        executor.shutdown();
        return pi / threads;
    }

    private List<Double> joinMultiThreadResult(int threads) throws InterruptedException {
        List<Double> partialResults = new ArrayList<>();
        while (partialResults.size() < threads) {
            Future<Double> resultFuture = service.take(); //blocks if none available
            try {
                partialResults.add(resultFuture.get());
                logger.info("Joined result: " + partialResults.size());
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        return partialResults;
    }

    private void submitMultiThreadGenerator(double totalCount, int threads) {
        for (int i = 1; i <= threads; i++) {
            logger.info("Submitted job: " + i);
            service.submit(() -> MonteCarloGenerator.generatePi(totalCount / threads));
        }
    }
}
