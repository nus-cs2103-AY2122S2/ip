import duke.Exceptions.DukeException;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.ToDo;
import duke.util.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void deadlineObjectCompare() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 10, 10, 6, 30);
        Deadline deadline = new Deadline("assignment 1", localDateTime);
        String expected = "[D][ ] assignment 1 (by: Oct 10 2019 6:30 AM)";
        assertEquals(deadline.toString(), expected);
    }

    @Test
    public void eventObjectCompare() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 10, 10, 6, 30);
        Event event = new Event("assignment 2", localDateTime);
        String expected = "[E][ ] assignment 2 (at: Oct 10 2019 6:30 AM)";
        assertEquals(event.toString(), expected);
    }

    @Test
    public void toDoObjectCompare() {
        ToDo toDo = new ToDo("assignment 2");
        String expected = "[T][ ] assignment 2";
        assertEquals(toDo.toString(), expected);
    }

    @Test
    public void parseMethodCompare() throws DukeException {
        String userInput = "/by 2018-10-10 1500";
        LocalDateTime localDateTime = LocalDateTime.of(2018, 10, 10, 15, 0, 0);
        LocalDateTime result = Parser.parseDateTime(userInput, "deadline");
        assertEquals(localDateTime.toString(), result.toString());
    }
}
