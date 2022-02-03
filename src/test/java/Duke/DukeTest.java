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
    public void mark_unmarked() {
        Task task = new Task("taskName");
        task.setMarked(true);
        assertEquals(task.isMarked(), true);
    }

    @Test
    public void toString_marked() {
        Task task = new Task("taskName");
        task.setMarked(true);
        assertEquals(task.toString(), "[X] taskName");
    }

    @Test
    public void toString_unmarked() {
        Task task = new Task("taskName");
        assertEquals(task.toString(), "[ ] taskName");
    }

}
