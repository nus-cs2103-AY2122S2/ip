package core.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToDoCreation() {
        assertEquals("[T][ ] read book", (ToDo.getInstance("read book")).toString());
    }

    @Test
    public void testDeadlineCreation() {
        assertEquals("[D][ ] read book (by: Apr 23 2022 6PM)", (Deadline.getInstance("read book", "2022-04-23 18:00")).toString());
    }

    @Test
    public void testEventCreation() {
        assertEquals("[E][ ] dance class (at: May 18 2021 4AM)", (Event.getInstance("dance class", "2021-05-18 04:00")).toString());
    }

}
