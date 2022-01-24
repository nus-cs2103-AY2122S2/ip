package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void byeCommand_testReturn_true(){
        TaskList taskList = new TaskList();
        Parser p = new Parser();
        try {
            assertEquals(true, p.takeInput("bye", taskList));
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

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

    @Test
    public void eventOutput_DateTimeException() throws DukeException{
        TaskList taskList = new TaskList();
        Parser p = new Parser();
        try{
            assertEquals(0, p.takeInput("event Mass /at 1231231", taskList));
        } catch(DukeException e) {
            assertEquals("Invalid input into date",e.getMessage());
        }
    }
}
