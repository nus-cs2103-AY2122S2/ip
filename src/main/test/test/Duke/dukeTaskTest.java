package Duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {

    @Test
    public void Test(){
        assertEquals(1, 1);
    }
}

public class todoTaskTest {
    @Test
    public void testStringOutput_todo() {
        Task todo = new ToDoTask("task");
        assertEquals("[T][ ] task", todo.toString());
    }
}

public class eventTaskTest {
    @Test
    public void testStringLoad_event() {
        Task event = new EventTask("event", false, "20-04-2022 09:00", "20-04-2022 10:00");
        assertEquals("[E][ ] event (at: 10-01-2022 1200 to 10-01-2022 1400)", event);
    }
}

