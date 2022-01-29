import duke.Parser;
import duke.exceptions.RequiredInformationMissingException;
import duke.exceptions.UnknownCommandException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.util.ArrayList;

/**
 * Junit testing class to run various tests on the Duke Chatbot.
 */

public class DukeTest {
    /**
     * Tests parsing by Parser class on a properly inputted event.
     * @throws RequiredInformationMissingException
     * @throws UnknownCommandException
     */
    @Test
    public void testEventInputParsing() throws RequiredInformationMissingException, UnknownCommandException {
        ArrayList<String> ls = Parser.parseInput("event brush teeth /at 12am");
        String[] expected = {"event", "brush teeth", "12am"};
        Assertions.assertArrayEquals(expected, ls.toArray());
    }

    /**
     * Tests parsing by Parser class on a properly inputted deadline.
     * @throws RequiredInformationMissingException
     * @throws UnknownCommandException
     */
    @Test
    public void testDeadlineInputParsing() throws RequiredInformationMissingException, UnknownCommandException {
        ArrayList<String> ls = Parser.parseInput("deadline brush teeth /by 12am");
        String[] expected = {"deadline", "brush teeth", "12am"};
        Assertions.assertArrayEquals(expected, ls.toArray());
    }

    /**
     * Tests parsing by Parser class on a properly inputted todo.
     * @throws RequiredInformationMissingException
     * @throws UnknownCommandException
     */
    @Test
    public void testToDoInputParsing() throws RequiredInformationMissingException, UnknownCommandException {
        ArrayList<String> ls = Parser.parseInput("todo brush teeth");
        String[] expected = {"todo", "brush teeth"};
        Assertions.assertArrayEquals(expected, ls.toArray());
    }
}
