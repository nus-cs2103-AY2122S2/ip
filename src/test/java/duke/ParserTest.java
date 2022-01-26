package duke;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ParserTest {
    @Test
    public void testParse() {
        Parser parserBye = new Parser("bye");
        Parser parserList = new Parser("list");

        assertEquals(parserBye.parse().getCommand(), new ParsedAnswer("bye", -1).getCommand());
        assertEquals(parserList.parse().getCommand(), new ParsedAnswer("list", -1).getCommand());
    }
}
