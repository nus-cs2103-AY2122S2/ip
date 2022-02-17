package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;


public class DukeTest {

    @Test
    public void testCreateToDo() {
        ToDo toDo = new ToDo("Some new ToDo");
        assertEquals("[T][ ] Some new ToDo", toDo.toString());
        System.out.println(toDo.toString());
    }

    @Test
    public void testCreateEvent() {
        LocalDate ld = LocalDate.parse("2022-12-22");
        Event event = new Event("Some new event", ld);
        assertEquals("[E][ ] Some new event (at: 2022-12-22)", event.toString());
        System.out.println(event.toString());
    }

    @Test
    public void testCreateDeadline() {
        LocalDate ld = LocalDate.parse("2022-12-22");
        Deadline deadline = new Deadline("Some new deadline", ld);
        assertEquals("[D][ ] Some new deadline (by: 2022-12-22)", deadline.toString());
        System.out.println(deadline.toString());
    }
}
