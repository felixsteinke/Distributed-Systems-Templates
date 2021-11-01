package app;

public class Application {

    public static void main(String[] args) {
        Generator generator = new Generator();

        long startTime = System.nanoTime();
        System.out.println(generator.generatePI(100_000_000));
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        GeneratorThread generatorThread = new GeneratorThread(4);

        startTime = System.nanoTime();
        System.out.println(generatorThread.generatePi(100_000_000));
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);
    }
}
