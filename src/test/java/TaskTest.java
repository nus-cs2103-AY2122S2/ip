import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testGetTypeAsPrefix() {
        assertEquals("T", new Todo("Test task 1").getTypeAsPrefix());

        assertEquals("D", new Deadline("Test task 2", true,
                LocalDate.of(2022, 1, 23)).getTypeAsPrefix());
        
        assertEquals("E", new Event("Test task 3", true,
                LocalDate.of(2022, 1, 23)).getTypeAsPrefix());
    }

    @Test
    public void testToString() {
        assertEquals("[T][ ] Test task 1",
                new Todo("Test task 1", false).toString());

        assertEquals("[D][X] Test task 2 (by: Jan 23 2022)",
                new Deadline("Test task 2", true,
                        LocalDate.of(2022, 1, 23)).toString());
    
        assertEquals("[E][X] Test task 3 (at: Jan 23 2022)",
                new Event("Test task 3", true,
                        LocalDate.of(2022, 1, 23)).toString());
    }

    @Test
    public void testFormatForFile() {
        assertEquals("T | 0 | Test task 1",
                new Todo("Test task 1", false).formatForFile());

        assertEquals("D | 1 | Test task 2 | 2022-01-23",
                new Deadline("Test task 2", true,
                        LocalDate.of(2022, 1, 23)).formatForFile());
    
        assertEquals("E | 1 | Test task 3 | 2022-01-23",
                new Event("Test task 3", true,
                        LocalDate.of(2022, 1, 23)).formatForFile());
    }
}
