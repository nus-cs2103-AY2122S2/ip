package spike.command;

import org.junit.jupiter.api.Test;
import spike.task.Event;
import spike.task.Task;
import spike.task.TaskList;
import spike.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void execute_givenTaskList_listAllTask() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Test");
        tasks.addTask(task);
        String expected = "Here are the task(s) in your list:\n"
                +  1 + "." + task;;
        assertEquals(expected, new ListCommand().execute(tasks));
    }
}
