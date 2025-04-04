package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> original = storage.toMap();
        for (String key : original.keySet()) {
            storage.unset(key);
        }
        for (Map.Entry<String, String> entry : original.entrySet()) {
            storage.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
