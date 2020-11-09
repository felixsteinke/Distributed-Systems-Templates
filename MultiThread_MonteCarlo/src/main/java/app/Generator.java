package app;

public class Generator {

    public double totalDrops;
    public double rawPositivDrops;
    public double scaledPositivDrops;

    public Generator() {

    }

    private double generateRandomNumber() {
        return Math.random();
    }

    private boolean checkDrop(double x, double y) {
        return Math.pow(x, 2) + Math.pow(y, 2) <= 1;
    }

    private boolean getRandomBoolean() {
        return checkDrop(generateRandomNumber(), generateRandomNumber());
    }


    public double generatePI(double totalCount) {
        for (long i = 0; i < totalCount; i++) {
            totalDrops++;
            if (getRandomBoolean())
                rawPositivDrops++;
        }
        scaledPositivDrops = rawPositivDrops * 4;
        return scaledPositivDrops / totalDrops;
    }
}
