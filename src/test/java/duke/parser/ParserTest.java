package duke.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ParserTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void parseCommand_byeCommand() {
        Parser parser = new Parser("bye");
        assertFalse(parser.processInput());
    }
}
