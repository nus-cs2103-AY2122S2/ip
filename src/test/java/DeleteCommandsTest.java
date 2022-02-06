import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import taskmaster.commands.DeleteCommands;
import taskmaster.exception.TaskmasterExceptions;
import taskmaster.task.TodoTask;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

import java.nio.file.Paths;

public class DeleteCommandsTest {
    private TaskList taskList = new TaskList();
    private Storage storage = new Storage(Paths.get("").toAbsolutePath() + "/data/Duke.txt");
    private UserInterface ui = new UserInterface();

    @Test
    @DisplayName("Test Delete 1")
    public void testDelete1Task() {
        taskList.add(new TodoTask("Hello World"));
        DeleteCommands command = new DeleteCommands("delete 1");
        try {
            command.execute(taskList, ui, storage);
            assertTrue(taskList.isEmpty());
        } catch (TaskmasterExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Delete 5")
    public void testDelete5Task() {
        taskList.add(new TodoTask("Hello World"));
        taskList.add(new TodoTask("Daddy"));
        taskList.add(new TodoTask("Mummy"));
        taskList.add(new TodoTask("Goomba"));
        taskList.add(new TodoTask("Oi"));
        DeleteCommands command = new DeleteCommands("delete 1");
        try {
            command.execute(taskList, ui, storage);
            command.execute(taskList, ui, storage);
            command.execute(taskList, ui, storage);
            command.execute(taskList, ui, storage);
            command.execute(taskList, ui, storage);
            assertTrue(taskList.isEmpty());
        } catch (TaskmasterExceptions e) {
            System.out.println(e.getMessage());
        }
    }


}
