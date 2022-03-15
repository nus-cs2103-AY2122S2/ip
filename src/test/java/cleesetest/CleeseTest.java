package cleesetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import cleese.Parser;
import cleese.Storage;
import task.TaskList;
import ui.Ui;

public class CleeseTest {
    private Parser parser = new Parser();
    private Storage storage = new Storage("test");
    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();

    @Test
    public void testParserWithEmptyListCommand() {
        try {
            assertEquals(parser.handleCommand("list", taskList, ui, storage),
                    "Sir I'm afraid you have no tasks right now");
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testParserWithByeCommand() {
        try {
            assertEquals(parser.handleCommand("bye", taskList, ui, storage),
                    "If that'll be all sir, i shall retire for the day.\n"
                    + "I'll be in my quarters if you require me");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void addToDoTaskSuccess() {
        try {
            assertEquals(parser.handleCommand("todo buy groceries", taskList, ui, storage),
                    "Got it. I've added this task:\n[T][ ] buy groceries\nNow you have 1 tasks in the list");
        } catch (Exception e) {
            fail();
        }
    }
}
