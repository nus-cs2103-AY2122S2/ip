package duke.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testGetIndex() {
        int test = Parser.getIndex("   mark     7");
        assertEquals(7, test);
    }

    @Test
    public void testInvalidNumberFormat() {
        int test = Parser.getIndex("   mark     7 5  2");
        assertEquals(-1, test);
    }

    @Test
    public void testGetCommand() {
        String test = Parser.getCommand("mark   5");
        assertEquals("mark", test);
    }
}
