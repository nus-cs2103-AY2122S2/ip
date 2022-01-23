import Tasks.Deadline;
import Tasks.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void deadlineObjectCompare() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 10, 10, 6, 30);
        Deadline deadline = new Deadline("assignment 1", localDateTime);
        String expected = "[D][ ] assignment 1 (by: Oct 10 2019 6:30 am)";
        assertEquals(deadline.toString(), expected);
    }

    @Test
    public void toDoObjectCompare() {
        ToDo toDo = new ToDo("assignment 2");
        String expected = "[T][ ] assignment 2";
        assertEquals(toDo.toString(), expected);
    }
}
