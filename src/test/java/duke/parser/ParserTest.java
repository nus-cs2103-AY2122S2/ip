package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void testParseList_correctFormat_noExceptions() {
        String[] input = "list".split(" ", 2);
        try {
            assertEquals(Command.LIST, Parser.parseString(input));
        } catch (DukeException e) {
            Assertions.fail();
        }
    }
}
