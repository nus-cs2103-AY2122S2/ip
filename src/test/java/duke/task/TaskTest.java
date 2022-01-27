package duke.task;

import duke.operations.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    public void testTodoStringConversion() {
        assertEquals("[T][ ] play amongus", new ToDo("play amongus").toString());
    }

    @Test
    public void testDeadlineStringConversion() {
        assertEquals("[D][ ] do project (by: 15 Oct 2022 07:00PM)",
                new Deadline("do project", Parser.convertStringToLocalDate("15-10-2022"),
                        Parser.convertStringToLocalTime("1900")).toString());
    }

    @Test
    public void testEventStringConversion() {
        assertEquals("[E][ ] eat japanese (at: 24 Dec 2022 09:00PM - 11:59PM)",
                new Event("eat japanese", Parser.convertStringToLocalDate("24-12-2022"),
                Parser.convertStringToLocalTime("2100"), Parser.convertStringToLocalTime("2359")).toString());
    }
}