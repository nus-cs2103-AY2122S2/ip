package spark;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import spark.exceptions.SparkException;
import spark.exceptions.formatexceptions.InvalidDeadlineParamsException;
import spark.exceptions.formatexceptions.InvalidMarkParamsException;
import spark.exceptions.formatexceptions.InvalidTodoParamsException;
import spark.parser.Parser;
import spark.parser.commands.commandtypes.AddDeadlineCommand;
import spark.parser.commands.commandtypes.AddTodoCommand;
import spark.parser.commands.commandtypes.Command;
import spark.parser.commands.commandtypes.MarkCommand;

public class ParserTest {
    @Test
    public void parseInput_inputValidAddToDo_returnsAddToDoCommand() {
        // a unit test for Parser#parseInput
        String input = "todo buy milk";
        try {
            Command command = Parser.parseInput(input);
            assertTrue(command instanceof AddTodoCommand);
        } catch (SparkException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseInput_inputAddToDoNoTitle_throwsException() {
        // a unit test for Parser#parseInput
        String input = "todo"; // missing title
        assertThrows(InvalidTodoParamsException.class, () -> Parser.parseInput(input));
    }

    @Test
    public void parseInput_inputValidAddDeadline_returnsAddDeadlineCommand() {
        // a unit test for Parser#parseInput
        String input = "deadline do homework /by 2-22-2022 1800";
        try {
            Command command = Parser.parseInput(input);
            assertTrue(command instanceof AddDeadlineCommand);
        } catch (SparkException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseInput_inputAddDeadlineNoTitle_throwsException() {
        // a unit test for Parser#parseInput
        String input = "deadline /by 2-22-2022 1800"; // missing title
        assertThrows(InvalidDeadlineParamsException.class, () -> Parser.parseInput(input));
    }

    @Test
    public void parseInput_inputAddDeadlineNoDate_throwsException() {
        // a unit test for Parser#parseInput
        String input = "deadline do homework"; // missing date
        assertThrows(InvalidDeadlineParamsException.class, () -> Parser.parseInput(input));
    }

    @Test
    public void parseInput_inputValidMarkTask_returnsMarkCommand() {
        // a unit test for Parser#parseInput
        String input = "mark 1";
        try {
            Command command = Parser.parseInput(input);
            assertTrue(command instanceof MarkCommand);
        } catch (SparkException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseInput_inputMarkTaskNoIndex_throwsException() {
        // a unit test for Parser#parseInput
        String input = "mark"; // missing task index
        assertThrows(InvalidMarkParamsException.class, () -> Parser.parseInput(input));
    }
}
