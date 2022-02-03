import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import command.Command;
import ui.Parser;

public class ParserTest {

    @Test
    public void todoBlank_dukeException() {
        try {
            Command c = Parser.parse("todo         ");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            String errorMessage = "OOPS!!! Can't find any info after your command! Have you typed it correctly?";
            assertEquals(errorMessage, e.getMessage());
        }
    }

}
