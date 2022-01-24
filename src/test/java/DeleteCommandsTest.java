import Taskmaster.Commands.AddCommands;
import Taskmaster.Commands.DeleteCommands;
import Taskmaster.Task.TodoTask;
import Taskmaster.Task.DeadlineTask;
import Taskmaster.Task.EventTask;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Taskmaster.util.TaskList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class DeleteCommandsTest {
    private final TaskList TASKLIST = new TaskList();

    @Test
    @DisplayName("Test Delete 1")
    public void testDelete1Task() {
        TASKLIST.add(new TodoTask("Hello World"));
        int initialSize = TASKLIST.currentSize;
        DeleteCommands command = new DeleteCommands("delete 1",TASKLIST);
        command.execute();
        assertTrue(initialSize != TASKLIST.currentSize);
    }

    @Test
    @DisplayName("Test Delete 5")
    public void testDelete5Task() {
        TASKLIST.add(new TodoTask("Hello World"));
        TASKLIST.add(new TodoTask("Daddy"));
        TASKLIST.add(new TodoTask("Mummy"));
        TASKLIST.add(new TodoTask("Goomba"));
        TASKLIST.add(new TodoTask("Oi"));
        int initialSize = TASKLIST.currentSize;
        DeleteCommands command = new DeleteCommands("delete 1",TASKLIST);
        command.execute();
        command.execute();
        command.execute();
        command.execute();
        command.execute();
        assertTrue(initialSize != TASKLIST.currentSize);
        assertEquals(TASKLIST.currentSize,0);
    }


}
