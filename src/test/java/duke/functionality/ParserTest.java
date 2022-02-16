package duke.functionality;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

public class ParserTest {
    @Test
    @DisplayName("The catch error works for unknown commands.")
    public void unknownCommand() {
        TaskList taskList = new TaskList();
        String input = "hello it's me";
        try {
            Parser.parse(input, taskList);
        } catch (DukeException error) {
            assertEquals(Parser.ERROR_UNKNOWN, error.getMessage());
        }
    }

    @Test
    @DisplayName("The catch error works for index out of bounds error.")
    public void indexOutOfBounds() {
        TaskList taskList = new TaskList();
        String input = "todo  ";
        try {
            Parser.parse(input, taskList);
        } catch (DukeException error) {
            assertEquals(Parser.ERROR_DESCRIPTION, error.getMessage());
        }
    }

    @Test
    @DisplayName("Convert date method works well.")
    public void dateConversion() {
        String dateString = "2022-01/30";
        try {
            Parser.convertDate(dateString);
        } catch (DukeException error) {
            assertEquals(Parser.ERROR_FORMAT_DATE, error.getMessage());
        }
    }

}
