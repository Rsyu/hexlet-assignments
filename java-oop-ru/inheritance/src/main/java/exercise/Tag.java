package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    protected String name;
    protected Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    // Форматирует атрибуты в строку: key="value"
    protected String stringifyAttributes() {
        return attributes.entrySet().stream()
            .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
            .collect(Collectors.joining(" "));
    }

    // Абстрактный метод, который реализуют наследники
    @Override
    public abstract String toString();
}
// END
