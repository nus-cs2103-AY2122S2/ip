package spike.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import spike.task.Task;
import spike.task.TaskList;
import spike.task.ToDo;

public class AddCommandTest {
    @Test
    public void execute_givenTaskList_addRelevantTask() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Test Task");

        String expected = "Got it. I've added this task:\n"
                + String.format("    %s\n", task)
                + String.format("Now you have %s task(s) in the list.", 1);

        assertEquals(expected, new AddCommand(task).execute(tasks));
    }
}

