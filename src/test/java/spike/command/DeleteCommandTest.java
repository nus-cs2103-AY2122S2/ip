package spike.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import spike.task.Task;
import spike.task.TaskList;
import spike.task.ToDo;



public class DeleteCommandTest {
    @Test
    public void execute_givenTaskList_deleteRelevantTask() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Test Task");
        tasks.addTask(task);

        String expected = " Noted. I've removed this task: \n"
                + String.format("    %s\n", task)
                + String.format("Now you have %s task(s) in the list.", 0);

        assertEquals(expected, new DeleteCommand(task).execute(tasks));
    }
}
