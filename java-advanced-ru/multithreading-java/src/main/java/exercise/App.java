package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
   public static Map<String, Integer> getMinMax(int[] numbers) {
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);

        // Запуск потоков
        maxThread.start();
        minThread.start();

        try {
            // Ожидание завершения обоих потоков
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            LOGGER.warning("Main thread interrupted");
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("max", maxThread.getMax());
        result.put("min", minThread.getMin());
        return result;
    }    
    // END
}
