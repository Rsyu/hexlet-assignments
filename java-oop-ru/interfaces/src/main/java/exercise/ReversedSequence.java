package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final String reversed;

    public ReversedSequence(String input) {
        this.reversed = new StringBuilder(input).reverse().toString();
    }

    @Override
    public int length() {
        return reversed.length();
    }

    @Override
    public char charAt(int index) {
        return reversed.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return reversed.subSequence(start, end);
    }

    @Override
    public String toString() {
        return reversed;
    }
}
// END
