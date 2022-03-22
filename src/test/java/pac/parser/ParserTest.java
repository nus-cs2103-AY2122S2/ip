package pac.parser;

import pac.PacException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Parser.parse("todotask buy groceries");
        } catch (PacException e) {
            assertEquals("Sorry! Invalid Command.",e.getMessage());
        }
    }
}
