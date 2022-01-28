import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import taskmaster.commands.AddCommands;
import taskmaster.task.DeadlineTask;
import taskmaster.task.EventTask;
import taskmaster.task.TodoTask;
import taskmaster.util.TaskList;



public class AddCommandsTest {
    private TaskList taskList = new TaskList();

    @Test
    @DisplayName("Test TodoTask creation")
    public void testAddTodoTask() {
        taskList.deleteAllTasks();
        AddCommands command = new AddCommands("todo me", taskList);
        command.execute();
        assertTrue(taskList.get(0) instanceof TodoTask);
    }

    @Test
    @DisplayName("Test DeadlineTask creation")
    public void testAddDeadlineTask() {
        taskList.deleteAllTasks();
        AddCommands command = new AddCommands("deadline eat some pizza /by 10/10/2010 1000", taskList);
        command.execute();
        assertTrue(taskList.get(0) instanceof DeadlineTask);
    }


    @Test
    @DisplayName("Test EventTask creation")
    public void testAddEventTask() {
        taskList.deleteAllTasks();
        AddCommands command = new AddCommands("event Spiderman No Way Home /at 01/01/2022 2359", taskList);
        command.execute();
        assertTrue(taskList.get(0) instanceof EventTask);
    }


}
