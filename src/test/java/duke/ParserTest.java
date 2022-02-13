package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Test for parser
 */
public class ParserTest {
    /**
     * test bye command quits and returns true
     */
    @Test
    public void byeCommand_testReturn_true() {
        TaskList taskList = new TaskList();
        Parser p = new Parser();
        try {
            assertEquals(true, p.takeInput("bye", taskList));
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * test that adding a task does not quit Duke
     */
    @Test
    public void todoTest_addToTaskList_false() {
        TaskList taskList = new TaskList();
        Parser p = new Parser();
        try {
            assertEquals(false, p.takeInput("todo go toilet", taskList));
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * test output when wrong input format for date and time
     * @throws DukeException for invalid input to date and time
     */
    @Test
    public void eventOutput_dateTimeException() throws DukeException {
        TaskList taskList = new TaskList();
        Parser p = new Parser();
        try {
            assertEquals(0, p.takeInput("event Mass /at 1231231", taskList));
        } catch (DukeException e) {
            assertEquals("Invalid input into date", e.getMessage());
        }
    }
}
