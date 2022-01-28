import duke.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    Parser parser = new Parser();
    @Test
    public void testGettingType() {
        assertEquals("bye", parser.getType("bye"),
                "Type of bye command should be `deadline`");

        assertEquals("deadline", parser.getType("deadline return book /by Sunday\n"),
                "Type of deadline command should be `deadline`");

        assertEquals("event", parser.getType("event project meeting /at Mon 2-4pm\n"),
                "Type of event command should be `event`");

        assertEquals("todo", parser.getType("todo borrow book\n"),
                "Type of todo command should be `todo`");

        assertEquals("mark", parser.getType("mark 3\n"),
                "Type of mark command should be `mark`");

        assertEquals("delete", parser.getType("delete 1\n"),
                "Type of delete command should be `delete`");
    }

    @Test
    public void testParsingEvent() {
        assertEquals("[E][ ] project meeting (at: 2021-12-27)",
                parser.parseEvent("event project meeting /at 2021-12-27").toString(),
                "Correct deadline object is created from parsing deadline command");
    }

    @Test
    public void testParsingDeadlines() {
        assertEquals("[D][ ] return book (by: 2021-12-27)",
                parser.parseDeadlines("deadline return book /by 2021-12-27").toString(),
                "Correct deadline object is created from parsing deadline command");
    }
}