package spike.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import spike.task.Task;
import spike.task.TaskList;
import spike.task.ToDo;

public class ToggleMarkCommandTest {
    @Test
    public void execute_givenTask_markAsDone() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Test");

        task.markAsDone();
        String expected = "Great! One more task done:\n" + task;
        task.markAsNotDone();

        assertEquals(expected, new ToggleMarkCommand(1, task).execute(tasks));
    }

    @Test
    public void execute_givenTask_markAsNotDone() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Test");

        String expected = "Oops, I've marked this task as not done yet:\n" + task;
        task.markAsDone();

        assertEquals(expected, new ToggleMarkCommand(0, task).execute(tasks));
    }
}
