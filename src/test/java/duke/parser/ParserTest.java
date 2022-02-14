package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.util.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    void parse_validInput_success() {
        String command = "todo a b c";
        try {
            assertTrue(new Parser().parse(command) instanceof AddCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parse_invalidInput_throwNoCommandException() {
        String command = "notACommand test";
        try {
            new Parser().parse(command);
            fail();
        } catch (Exception e) {
            assertEquals("Oops! try another command!", e.getMessage());
        }
    }

    @Test
    void parse_invalidArgument_throwInvalidArgumentException() {
        String command = "todo";
        try {
            new Parser().parse(command);
            fail();
        } catch (Exception e) {
            assertEquals("Oops! please check your input's argument again!", e.getMessage());
        }
    }
}
