import duke.command.ListCommand;
import duke.command.UnkownCommandException;
import duke.common.DukeException;
import duke.parser.Parser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test the parser class.
 */
public class ParserTest {

    private static final ByteArrayOutputStream OUT = new ByteArrayOutputStream();
    private static final PrintStream SYSTEM_OUT = System.out;

    @BeforeAll
    public static void setUpStream() {
        System.setOut(new PrintStream(OUT));
    }

    @AfterAll
    public static void restoreStream() {
        System.setOut(SYSTEM_OUT);
    }

    @Test
    public void parseTokens_listCommand_success() {
        try {
            assertEquals(Parser.parse("list").getCommandType(),
                    new ListCommand().getCommandType());
        } catch (DukeException exception) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void parseTokens_unkownCommand_exceptionThrown() {

        UnkownCommandException thrown = assertThrows(UnkownCommandException.class,
                () -> Parser.parse("hello").getCommandType());
        Assertions.assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                thrown.getMessage());

    }
}
