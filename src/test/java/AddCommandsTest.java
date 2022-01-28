import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import taskmaster.commands.AddCommands;
import taskmaster.task.DeadlineTask;
import taskmaster.task.EventTask;
import taskmaster.task.TodoTask;
import taskmaster.util.TaskList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandsTest {
    private final TaskList TASKLIST = new TaskList();

    @Test
    @DisplayName("Test TodoTask creation")
    public void testAddTodoTask() {
        TASKLIST.deleteAllTasks();
        AddCommands command = new AddCommands("todo me", TASKLIST);
        command.execute();
        assertTrue(TASKLIST.get(0) instanceof TodoTask);
    }

    @Test
    @DisplayName("Test DeadlineTask creation")
    public void testAddDeadlineTask() {
        TASKLIST.deleteAllTasks();
        AddCommands command = new AddCommands("deadline eat some pizza /by 10/10/2010 1000", TASKLIST);
        command.execute();
        assertTrue(TASKLIST.get(0) instanceof DeadlineTask);
    }


    @Test
    @DisplayName("Test EventTask creation")
    public void testAddEventTask() {
        TASKLIST.deleteAllTasks();
        AddCommands command = new AddCommands("event Spiderman No Way Home /at 01/01/2022 2359", TASKLIST);
        command.execute();
        assertTrue(TASKLIST.get(0) instanceof EventTask);
    }


}
