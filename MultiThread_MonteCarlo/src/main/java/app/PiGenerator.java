package app;

import java.util.logging.Logger;

public class PiGenerator {
    private static final Logger logger = Logger.getLogger(PiGenerator.class.getName());
    private double totalDrops;
    private double rawPositiveDrops;

    public double generatePI(double totalCount) {
        logger.info("Calculate " + totalCount + " drops.");
        for (long i = 0; i < totalCount; i++) {
            totalDrops++;
            if (generateMonteCarloBool())
                rawPositiveDrops++;
        }
        double pi = rawPositiveDrops * 4 / totalDrops;
        logger.info("Calculated pi: " + pi);
        return pi;
    }

    private boolean generateMonteCarloBool() {
        return checkMonteCarloDrop(generateRandomNumber(), generateRandomNumber());
    }

    private boolean checkMonteCarloDrop(double x, double y) {
        return Math.pow(x, 2) + Math.pow(y, 2) <= 1;
    }

    private double generateRandomNumber() {
        return Math.random();
    }
}
