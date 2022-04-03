package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testParsing() {
        assertEquals("", Parser.parse("hi"));
        assertEquals("", Parser.parse("todo"));
        assertEquals("", Parser.parse("todo "));
        assertEquals("", Parser.parse("event "));
        assertEquals("", Parser.parse("event birthday at"));
        assertEquals("", Parser.parse("event birthday at "));
        assertEquals("event", Parser.parse("event birthday at 1500"));
        assertEquals("", Parser.parse("deadline cs2103t week 3 ip tasks by"));
        assertEquals("", Parser.parse("deadline cs2103t week 3 ip tasks by "));
        assertEquals("deadline", Parser.parse("deadline cs2103t week 3 ip tasks by 2022/01/27 2359"));
    }
}
