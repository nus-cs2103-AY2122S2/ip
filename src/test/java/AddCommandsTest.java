import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lucifer.commands.ParseCommands;
import lucifer.task.Deadline;
import lucifer.task.Event;
import lucifer.task.Todo;
import lucifer.tasklist.TaskList;

public class AddCommandsTest {
    private final TaskList tasks = new TaskList();
    private final ParseCommands parser = new ParseCommands(tasks);

    @Test
    public void addDeadLineTest() {
        parser.parseCommand("deadline do homework /by 12-12-2022 1800");
        assertTrue(tasks.getTaskList().get(0) instanceof Deadline);
    }

    @Test
    public void addEventTest() {
        parser.parseCommand("event do homework /at 12-12-2022 1800");
        assertTrue(tasks.getTaskList().get(0) instanceof Event);
    }
    @Test
    public void addToDo() {
        parser.parseCommand("todo do homework");
        assertTrue(tasks.getTaskList().get(0) instanceof Todo);
    }

}

