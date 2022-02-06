package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.MarkCommand;
import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.ui.Ui;

class ParserTest {

    @Test
    void testParse() {
        Parser parser = new Parser(new Ui());
        assertEquals(true, parser.parse("deadline 444 /by 12/12/2222 0800") instanceof AddCommand);
        assertEquals(true, parser.parse("bye") instanceof ExitCommand);
        assertEquals(true, parser.parse("delete 3") instanceof DeleteCommand);
        assertEquals(true, parser.parse("mark") instanceof MarkCommand);
    }

    @Test
    void testParseDescription() throws InvalidArgumentException {
        Parser parser = new Parser(new Ui());
        assertEquals("TodoDescription", parser.parseDescription("todo TodoDescription"));
    }

    @Test
    void testParseDeadline() throws InvalidArgumentException {
        Parser parser = new Parser(new Ui());
        assertTrue(Arrays.equals(new String[]{"taskDescription", "taskDeadline"},
                parser.parseDeadline("deadline taskDescription /by taskDeadline")));
    }

    @Test
    void testParseEvent() throws InvalidArgumentException {
        Parser parser = new Parser(new Ui());
        assertTrue(Arrays.equals(new String[]{"eventDescription", "eventAt"},
                parser.parseEvent("event eventDescription /at eventAt")));
    }
}
