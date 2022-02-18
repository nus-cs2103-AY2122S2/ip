package Duke;


import org.junit.jupiter.api.Test;
import duke.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }


    @Test
    public void toString_unmarked() {
        Task task = new Task("taskName");
        assertEquals(task.toString(), "[ ] taskName");
    }

}
