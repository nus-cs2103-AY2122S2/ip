package duke.parser;

import duke.command.*;
import org.junit.jupiter.api.Test;

import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    void parse_normalInput_writtenCorrectly() {
        String inputString = "todo Eat my breakfast";
        assertTrue(Parser.parse(inputString) instanceof AddCommand);

        inputString = "event Eat my lunch /at 2012-06-03";
        assertTrue(Parser.parse(inputString) instanceof AddCommand);

        inputString = "todo Eat my dinner /by 2012-06-03";
        assertTrue(Parser.parse(inputString) instanceof AddCommand);

        inputString = "bye";
        assertTrue(Parser.parse(inputString) instanceof ExitCommand);

        inputString = "list";
        assertTrue(Parser.parse(inputString) instanceof ListCommand);

        inputString = "mark 1";
        assertTrue(Parser.parse(inputString) instanceof MarkCommand);

        inputString = "unmark 1";
        assertTrue(Parser.parse(inputString) instanceof UnmarkCommand);

        inputString = "delete 1";
        assertTrue(Parser.parse(inputString) instanceof DeleteCommand);

        inputString = "ist a new day";
        assertTrue(Parser.parse(inputString) instanceof HelpCommand);
    }

    @Test
    void parse_emptyInput_helpCommandInitiated() {
        String inputString = " ";
        assertTrue(Parser.parse(inputString) instanceof HelpCommand);
    }
}
