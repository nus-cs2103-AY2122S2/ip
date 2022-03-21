package gary;
import gary.exception.GaryException;
import gary.ui.Parser;
import gary.ui.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for correctness of gary.Parser class
 */
public class ParserTest {

    /**
     * Test for the correctness of parse() method
     * Checking if User Input "bye" will terminate chat-bot
     */
    @Test
    public void testBye() {
        try {
            Parser p = new Parser();
            TaskList t = new TaskList(new ArrayList<String>());
            assertEquals("Bye, have a productive day!", p.parse("bye", t));
        } catch (GaryException e) {
            System.out.println("testBye: error");
        }
    }
}
