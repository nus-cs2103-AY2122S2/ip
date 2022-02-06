import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ExceptionHandler;
import duke.TaskList;
import duke.ToDos;


/**
 * class to test
 */
public class DukeTest {

    @Test
    public void taskListAddTest() throws ExceptionHandler {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDos("todo walk dog"));
        assertEquals("todo walk dog", taskList.getListOfTasks().get(0).getDescription());
    }

}


