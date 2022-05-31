package app;

import java.util.logging.Logger;

public class MonteCarloGenerator {
    private static final Logger logger = Logger.getLogger(MonteCarloGenerator.class.getName());

    public static double generatePi(double totalDrops) {
        logger.info("Calculate " + totalDrops + " drops in circle.");

        double dropsInCircle = 0;
        for (long i = 0; i < totalDrops; i++) {
            if (isDropInCircle(Math.random(), Math.random()))
                dropsInCircle++;
        }
        double pi = dropsInCircle * 4 / totalDrops;

        logger.info("Calculated pi: " + pi);
        return pi;
    }

    /**
     * @param x 0 <= value < 1
     * @param y 0 <= value < 1
     * @return boolean after the monte carlo rule
     */
    private static boolean isDropInCircle(double x, double y) {
        return Math.pow(x, 2) + Math.pow(y, 2) <= 1;
    }
}
