package spike.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import spike.task.Task;
import spike.task.TaskList;
import spike.task.ToDo;



public class FindCommandTest {
    @Test
    public void execute_givenTaskList_findMatchingTask() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Test Task");
        tasks.addTask(task);

        String expected = "Here are the matching tasks in your list:\n"
                + 1 + "." + task;

        assertEquals(expected, new FindCommand("Test").execute(tasks));
    }
}
