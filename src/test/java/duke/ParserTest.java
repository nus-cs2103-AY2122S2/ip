package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void makeCommandTest() {
        Parser p = new Parser();
        Command c = p.makeCommand("bye");
        boolean result = c instanceof ByeCommand;
        assertEquals(result, true);
    }
}
