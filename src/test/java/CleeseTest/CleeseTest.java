package CleeseTest;

import cleese.*;

import task.TasksList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CleeseTest {
    private Parser parser = new Parser();
    private Storage storage = new Storage("placeholder");
    private TasksList tasksList = new TasksList();
    private Ui ui = new Ui();

    @Test
    public void testListCommand() {
        try {
            assertEquals(parser.handleCommand("list", tasksList,ui,storage),"list" );
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testByeCommand() {
        try {
            assertEquals(parser.handleCommand("bye", tasksList,ui,storage),"bye" );
        } catch (Exception e) {
            fail();
        }
    }
}