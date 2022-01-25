package spike.command;

import org.junit.jupiter.api.Test;
import spike.task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncorrectCommandTest {
    @Test
    public void execute_givenAnyMsg_returnMsg() {
        TaskList tasks = new TaskList();
        assertEquals("Test", new IncorrectCommand("Test").execute(tasks));
    }
}
