package Duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void terminateTest() {
        Parser parser = new Parser();
        parser.terminate();
        assertTrue(parser.isTerminated);
    }
}
