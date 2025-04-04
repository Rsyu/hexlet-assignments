package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    @Override
    public String toString() {
        String attrString = stringifyAttributes();
        return attrString.isEmpty()
            ? String.format("<%s>", name)
            : String.format("<%s %s>", name, attrString);
    }
}
// END
