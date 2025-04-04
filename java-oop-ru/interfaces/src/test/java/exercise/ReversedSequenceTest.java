package exercise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReversedSequenceTest {

    @Test
    void testToString() {
        CharSequence seq = new ReversedSequence("abcdef");
        assertEquals("fedcba", seq.toString());
    }

    @Test
    void testCharAt() {
        CharSequence seq = new ReversedSequence("abcdef");
        assertEquals('f', seq.charAt(0));
        assertEquals('e', seq.charAt(1));
        assertEquals('a', seq.charAt(5));
    }

    @Test
    void testLength() {
        CharSequence seq = new ReversedSequence("abcdef");
        assertEquals(6, seq.length());
    }

    @Test
    void testSubSequence() {
        CharSequence seq = new ReversedSequence("abcdef");
        assertEquals("edc", seq.subSequence(1, 4).toString());
    }
}

