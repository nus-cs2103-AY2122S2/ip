package spike.parser;

import org.junit.jupiter.api.Test;
import spike.command.IncorrectCommand;
import spike.task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_invalidCommand_incorrectCommandObject() {
        Parser parser = new Parser();
        Boolean isIncorrectCommand = parser.parseCommand("random text",
                new TaskList()) instanceof IncorrectCommand;
        assertEquals(true, isIncorrectCommand);
    }
}
