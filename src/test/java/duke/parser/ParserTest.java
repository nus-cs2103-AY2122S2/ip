package duke.parser;

import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.io.Storage;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParsing_validExitCmd_success() {
        Parser parser = new Parser(new Ui(), new Storage());
        assertEquals(true, parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void testParsing_invalidCmd_success() {
        Parser parser = new Parser(new Ui(), new Storage());
        assertEquals(true, parser.parse("mkflewjlkfw") instanceof InvalidCommand);
    }
}
