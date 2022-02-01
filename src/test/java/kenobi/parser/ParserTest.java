package kenobi.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void dateFormatException_invalidDateFormat_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("deadline homework /by 123123123"));
            fail();
        } catch (ParseException e) {
            assertEquals("Whoopsie-Daisy :( I don't understand that date", e.getMessage());
        }
    }

    @Test
    public void incompleteCommand_oneStringCommand_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("event"));
            fail();
        } catch (ParseException e) {
            assertEquals("Whoopsie-Daisy :( the command seems to be incomplete", e.getMessage());
        }
    }
}
