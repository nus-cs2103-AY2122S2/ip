import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import taskmaster.commands.DeleteCommands;
import taskmaster.task.TodoTask;
import taskmaster.util.TaskList;

public class DeleteCommandsTest {
    private TaskList taskList = new TaskList();

    @Test
    @DisplayName("Test Delete 1")
    public void testDelete1Task() {
        taskList.add(new TodoTask("Hello World"));
        DeleteCommands command = new DeleteCommands("delete 1", taskList);
        command.execute();
        assertTrue(taskList.isEmpty());
    }

    @Test
    @DisplayName("Test Delete 5")
    public void testDelete5Task() {
        taskList.add(new TodoTask("Hello World"));
        taskList.add(new TodoTask("Daddy"));
        taskList.add(new TodoTask("Mummy"));
        taskList.add(new TodoTask("Goomba"));
        taskList.add(new TodoTask("Oi"));
        DeleteCommands command = new DeleteCommands("delete 1", taskList);
        command.execute();
        command.execute();
        command.execute();
        command.execute();
        command.execute();
        assertTrue(taskList.isEmpty());
    }


}
