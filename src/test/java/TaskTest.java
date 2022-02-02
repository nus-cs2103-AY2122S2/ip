
import task.Deadline;
import task.Event;
import task.ToDo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        assertEquals("[T][ ] Eat", new ToDo("Eat").toString());
        assertEquals("[D][ ] Sleep (by: Jan 1 2019)", new Deadline("Sleep", "2019-01-01").toString());
        assertEquals("[E][ ] Walk (at: Jan 2 2020)", new Event("Walk", "2020-01-02").toString());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals(" ", new ToDo("Eat").getStatusIcon());
        assertEquals(" ", new Deadline("Sleep", "2019-01-01").getStatusIcon());
        assertEquals(" ", new Event("Walk", "2020-01-02").getStatusIcon());
    }
    @Test
    public void saveString() {
        assertEquals("T|0|Eat", new ToDo("Eat").saveString());
        assertEquals("D|0|Sleep|2019-01-01", new Deadline("Sleep", "2019-01-01").saveString());
        assertEquals("E|0|Walk|2020-01-02", new Event("Walk", "2020-01-02").saveString());
    }

}
