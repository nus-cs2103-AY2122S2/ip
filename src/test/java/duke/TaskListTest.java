package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void TaskListTest() {
        Task t1 = new ToDo("todo write notes for cs3244");
        Task t2 = new Event("movie night", "rvrc at 7pm");
        assertEquals("[T] [ ] todo write notes for cs3244", t1.toString());
        assertEquals("[E] [ ] movie night (rvrc at 7pm)", t2.toString());
    }
}