package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void makeCommand_ByeAsInput_ReturnByeCommand() {
        Parser p = new Parser();
        Command c = p.makeCommand("bye");
        boolean result = c instanceof ByeCommand;
        assertEquals(result, true);
    }

    @Test
    public void makeCommand_ListAsInput_ReturnListCommand() {
        Parser p = new Parser();
        Command c = p.makeCommand("list");
        boolean result = c instanceof ListCommand;
        assertEquals(result, true);
    }
}
