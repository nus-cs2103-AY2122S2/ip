package spike.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import spike.task.Task;
import spike.task.TaskList;
import spike.task.ToDo;


public class ListCommandTest {
    @Test
    public void execute_givenTaskList_listAllTask() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Test");
        tasks.addTask(task);
        String expected = "Here are the task(s) in your list:\n"
                + 1 + "." + task;;
        assertEquals(expected, new ListCommand().execute(tasks));
    }
}
