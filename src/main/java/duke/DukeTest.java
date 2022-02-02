package duke;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void ExitTest(){
        Command command = new ExitCommand();
        assertEquals(true,command.isExit());
    }

    @Test
    public void TaskListAddTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDos("todo walk dog"));
        assertEquals("todo walk dog", taskList.getListOfTasks().get(0).getDescription());
    }

    @Test
    public void TaskListRemoveTest(){
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDos("todo walk dog"));
        taskList.removeTask(0);
        assertEquals(0,taskList.getSize());
    }

}
