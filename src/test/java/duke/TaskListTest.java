package duke;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void TaskListTest() {
        Task t1 = new ToDo("todo run");
        Task t2 = new Event("event swim", "at 12pm");
        assertEquals("[T][ ] todo run", t1.toString());
        assertEquals("[E][ ] event swim (at 12pm)", t2.toString());
    }
}
