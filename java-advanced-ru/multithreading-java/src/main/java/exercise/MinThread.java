package exercise;

// BEGIN
import java.util.logging.Logger;

public class MinThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(MinThread.class.getName());

    private final int[] numbers;
    private int min;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public void run() {
        LOGGER.info("Thread " + Thread.currentThread().getName() + " started");

        min = numbers[0];
        for (int num : numbers) {
            if (num < min) {
                min = num;
            }
        }

        LOGGER.info("Thread " + Thread.currentThread().getName() + " finished");
    }

    public int getMin() {
        return min;
    }
}
// END
