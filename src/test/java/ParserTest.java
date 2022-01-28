import duke.Parser;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_incorrectInput_exceptionThrown() throws DukeException {
        try {
            assertEquals(0, Parser.parse("nonsense command", "random stuff"));
            fail();
        } catch (DukeException de){
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", de.getMessage());
        }
    }

    @Test
    public void parse_incorrectDate_exceptionThrow() throws DukeException {
        try {
            assertEquals(0, Parser.parse("deadline", "cs2013 homework /by 2022/20/01"));
            fail();
        } catch (DukeException de){
            assertEquals("Please enter a date with the format yyyy-mm-dd", de.getMessage());
        }
    }

}
