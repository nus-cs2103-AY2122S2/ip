package spike.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import spike.command.IncorrectCommand;
import spike.task.TaskList;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void parseCommand_invalidCommand_incorrectCommand() {
        Boolean isIncorrectCommand = parser.parseCommand("random text",
                new TaskList()) instanceof IncorrectCommand;
        assertTrue(isIncorrectCommand);
    }

    @ParameterizedTest
    @ValueSource(strings = {"delete", "find", "mark", "unmark", "deadline", "event", "todo"})
    public void parseCommand_incompleteCommand_incorrectCommand(String command) {
        Boolean isIncorrectCommand = parser.parseCommand(command,
                new TaskList()) instanceof IncorrectCommand;
        assertTrue(isIncorrectCommand);
    }
}
