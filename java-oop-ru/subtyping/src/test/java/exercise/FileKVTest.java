package exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

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

        assertThat(storage.get("key", "default")).isEqualTo("value");
        assertThat(storage.get("unknown", "default")).isEqualTo("default");

        storage.set("name", "Hexlet");
        assertThat(storage.get("name", "default")).isEqualTo("Hexlet");

        storage.unset("name");
        assertThat(storage.get("name", "default")).isEqualTo("default");

        assertThat(storage.toMap()).isEqualTo(Map.of("key", "value"));
    }

    @Test
    void testSwap() {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("a", "1", "b", "2"));

        App.swapKeyValue(storage);

        assertThat(storage.get("1", "default")).isEqualTo("a");
        assertThat(storage.get("2", "default")).isEqualTo("b");
        assertThat(storage.get("a", "default")).isEqualTo("default");
    }    
    // END
}
