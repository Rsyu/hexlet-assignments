package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String body;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        String attrString = stringifyAttributes();
        String childrenString = children.stream()
            .map(Tag::toString)
            .collect(Collectors.joining());

        String openTag = attrString.isEmpty()
            ? String.format("<%s>", name)
            : String.format("<%s %s>", name, attrString);

        return openTag + body + childrenString + String.format("</%s>", name);
    }
}
// END
