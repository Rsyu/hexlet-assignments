package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private final String text;
    private final TagInterface child;

    public LabelTag(String text, TagInterface child) {
        this.text = text;
        this.child = child;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", text, child.render());
    }
}
// END
