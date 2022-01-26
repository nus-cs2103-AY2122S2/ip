package duke.ui;

import duke.exceptions.TaskIndexException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiManagerTest {

   @Test
    public void printLine_success() {
       assertEquals("-------------------------------------------",
               new UiManager().getLine());
   }

   @Test
    public void parseTodoCommand_success() throws TaskIndexException {
       String[] s = {"todo", "hello"};
       assertEquals(Arrays.toString(s), Arrays.toString(new UiManager().parseCommand("todo hello")));
   }

    @Test
    public void parseTodoCommand_exceptionThrown() throws TaskIndexException {
       try {
           new UiManager().parseCommand("todo");
       } catch (TaskIndexException e) {
           assertEquals("OOPS!!! You didn't give me a proper 'todo' typed task to include!",
                   e.toString());
       }
    }
}
