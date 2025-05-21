package exercise;

// BEGIN
import java.util.logging.Logger;

public class MaxThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(MaxThread.class.getName());

    private final int[] numbers;
    private int max;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public void run() {
        LOGGER.info("Thread " + Thread.currentThread().getName() + " started");

        max = numbers[0];
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }

        LOGGER.info("Thread " + Thread.currentThread().getName() + " finished");
    }

    public int getMax() {
        return max;
    }
}
// END
