package app;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.nanoTime();
        double pi = MonteCarloGenerator.generatePi(100_000_000);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Single thread result: " + pi);
        System.out.println("Single thread time: " + ((double) elapsedTime) / 1_000_000_000 + " seconds");

        startTime = System.nanoTime();
        pi = new MultiThreadGenerator(4).generatePi(100_000_000);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("Multi thread result: " + pi);
        System.out.println("Multi thread time: " + ((double) elapsedTime) / 1_000_000_000 + " seconds");
    }
}
