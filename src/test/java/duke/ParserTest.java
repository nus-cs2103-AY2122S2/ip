package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    @Test
    public void testParse() {
        Parser parserBye = new Parser("bye");
        Parser parserList = new Parser("list");

        assertEquals(parserBye.parse().getCommand(), new ParsedAnswer("bye", -1).getCommand());
        assertEquals(parserList.parse().getCommand(), new ParsedAnswer("list", -1).getCommand());
    }
}
