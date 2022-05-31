package app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class PiGeneratorMultiThread {
    private static final Logger logger = Logger.getLogger(PiGeneratorMultiThread.class.getName());
    private final int threads;
    private final CompletionService<Double> service;


    public PiGeneratorMultiThread(int threads) {
        this.threads = threads;
        this.service = new ExecutorCompletionService<>(Executors.newFixedThreadPool(threads));
    }

    public double generatePi(double totalCount) throws InterruptedException {
        submitMultiThreadGenerator(totalCount, threads);
        double pi = 0;
        for (double partialResult : joinMultiThreadResult(threads)) {
            pi += partialResult;
        }
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
            submitPartialGenerator(totalCount / threads);
        }
    }

    private void submitPartialGenerator(double partialCount) {
        service.submit(() -> {
            PiGenerator generator = new PiGenerator();
            return generator.generatePI(partialCount);
        });
    }
}
