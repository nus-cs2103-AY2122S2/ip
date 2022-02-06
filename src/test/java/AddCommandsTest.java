import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import taskmaster.commands.AddCommands;
import taskmaster.exception.TaskmasterExceptions;
import taskmaster.task.DeadlineTask;
import taskmaster.task.EventTask;
import taskmaster.task.TodoTask;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

import java.nio.file.Paths;


public class AddCommandsTest {
    private TaskList taskList = new TaskList();
    private Storage storage = new Storage(Paths.get("").toAbsolutePath() + "/data/Duke.txt");
    private UserInterface ui = new UserInterface();

    @Test
    @DisplayName("Test TodoTask creation")
    public void testAddTodoTask() {
        taskList.deleteAllTasks();
        AddCommands command = new AddCommands("todo me");
        try {
            command.execute(taskList, ui, storage);
            assertTrue(taskList.get(0) instanceof TodoTask);
        } catch (TaskmasterExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Test DeadlineTask creation")
    public void testAddDeadlineTask() {
        taskList.deleteAllTasks();
        AddCommands command = new AddCommands("deadline eat some pizza /by 10/10/2010 1000");
        try {
            command.execute(taskList, ui, storage);
            assertTrue(taskList.get(0) instanceof DeadlineTask);
        } catch (TaskmasterExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Test EventTask creation")
    public void testAddEventTask() {
        taskList.deleteAllTasks();
        AddCommands command = new AddCommands("event Spiderman No Way Home /at 01/01/2022 2359");
        try {
            command.execute(taskList, ui, storage);
            assertTrue(taskList.get(0) instanceof EventTask);
        } catch (TaskmasterExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}
