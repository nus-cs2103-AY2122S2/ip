package Duke;


import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }


    @Test
    public void unmarked_task_test() {
        Task task = new Task("task");
        assertEquals(task.toString(), "[ ] task");
    }

    @Test
    public void todo_task_test(){
        Task task = new Todo("task");
        assertEquals(task.getTaskType(), "T");
    }


}
