import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import taskmaster.commands.DeleteCommands;
import taskmaster.task.TodoTask;
import taskmaster.util.TaskList;

public class DeleteCommandsTest {
    private final TaskList TASKLIST = new TaskList();

    @Test
    @DisplayName("Test Delete 1")
    public void testDelete1Task() {
        TASKLIST.add(new TodoTask("Hello World"));
        DeleteCommands command = new DeleteCommands("delete 1",TASKLIST);
        command.execute();
        assertTrue(TASKLIST.isEmpty());
    }

    @Test
    @DisplayName("Test Delete 5")
    public void testDelete5Task() {
        TASKLIST.add(new TodoTask("Hello World"));
        TASKLIST.add(new TodoTask("Daddy"));
        TASKLIST.add(new TodoTask("Mummy"));
        TASKLIST.add(new TodoTask("Goomba"));
        TASKLIST.add(new TodoTask("Oi"));
        DeleteCommands command = new DeleteCommands("delete 1",TASKLIST);
        command.execute();
        command.execute();
        command.execute();
        command.execute();
        command.execute();
        assertTrue(TASKLIST.isEmpty());
    }


}
