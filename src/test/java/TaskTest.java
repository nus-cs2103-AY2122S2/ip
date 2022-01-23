import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testGetTypeAsPrefix() {
        assertEquals("D", new Deadline("Test task 1", true,
                LocalDate.of(2022, 1, 23)).getTypeAsPrefix());
    }

    @Test
    public void testToString() {
        assertEquals("[D][X] Test task 1 (by: Jan 23 2022)",
                new Deadline("Test task 1", true,
                        LocalDate.of(2022, 1, 23)).toString());
    }
}
