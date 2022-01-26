package duke.command;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testParser_valid_success() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ByeCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
        assertTrue(Parser.parse("unmark 1") instanceof MarkCommand);
        assertTrue(Parser.parse("todo TodoTitleGoesHere") instanceof CreateCommand);
        assertTrue(Parser.parse("deadline DeadlineTitleGoesHere") instanceof CreateCommand);
        assertTrue(Parser.parse("event EventTitleGoesHere") instanceof CreateCommand);
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void testParser_invalid_exceptionThrown(){
        try {
            Parser.parse("invalid");
            fail();
        } catch(DukeException e){
            assertEquals("OOPS!!! You have entered an invalid command :(" , e.getMessage());
        }
    }
}
