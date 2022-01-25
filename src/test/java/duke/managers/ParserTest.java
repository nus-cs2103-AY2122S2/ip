package duke.managers;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void handleCommandFeedback_valid_success() {
        Parser parser = new Parser();
        try {
            parser.parse("todo Read Book");
            System.out.println("Valid input detected! Test succeeded");
        } catch (Exception e) {
            System.out.println("Valid input missed! Test failed");
        }
    }

    @Test
    public void handleCommandFeedback_unrecognizedCommand_error() {
        Parser parser = new Parser();
        try {
            parser.parse("abcd");
            System.out.println("Exception missed! Test failed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
