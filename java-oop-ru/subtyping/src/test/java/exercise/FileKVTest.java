package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
 @Test
    void testSetGetUnset() {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "value"));

        // get existing key
        assertThat(storage.get("key", "default")).isEqualTo("value");

        // get missing key
        assertThat(storage.get("unknown", "default")).isEqualTo("default");

        // set new key
        storage.set("name", "Hexlet");
        assertThat(storage.get("name", "default")).isEqualTo("Hexlet");

        // unset key
        storage.unset("name");
        assertThat(storage.get("name", "default")).isEqualTo("default");

        // toMap
        assertThat(storage.toMap()).isEqualTo(Map.of("key", "value"));
    }

    @Test
    void testSwapKeyValue() {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("a", "1", "b", "2"));
        App.swapKeyValue(storage);

        // Проверяем, что значения и ключи поменялись
        assertThat(storage.get("1", "default")).isEqualTo("a");
        assertThat(storage.get("2", "default")).isEqualTo("b");
        assertThat(storage.get("a", "default")).isEqualTo("default");
    }
    
    // END
}
