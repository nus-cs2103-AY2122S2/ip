package athena.parser;

import athena.commands.TodoCommand;
import athena.exceptions.InputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void getCommand_todoCommand_success() throws InputException {
        assertEquals(new TodoCommand("groceries"), Parser.getCommand("todo groceries "));
        assertEquals(new TodoCommand("go shop"), Parser.getCommand("todo go shop "));
    }

    @Test
    void getCommand_todoWithoutTaskName_inputExceptionThrown() {
        String todoWithoutTaskName = "todo   ";
        // Make sure the right exception is thrown
        assertThrows(InputException.class, () -> Parser.getCommand(todoWithoutTaskName));

        // Make sure the message is correct
        try {
            Parser.getCommand(todoWithoutTaskName);
            fail();
        } catch (InputException e) {
            assertEquals("Error. Please provide a task name.", e.getMessage());
        }
    }

}