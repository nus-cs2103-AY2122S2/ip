import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import saitama.tasks.Deadline;
import saitama.tags.RecurFrequency;
import saitama.tasks.Task;
import saitama.tasks.ToDo;

class TaskTest {

    private Task task = new ToDo("Eat", null);
    private Task recursiveDeadline = new Deadline("CS2103T", LocalDateTime.of(2022, 12, 28, 20, 0), true,
                RecurFrequency.WEEKLY, LocalDate.of(2000, 12, 20));

    @Test
    void testToString() {
        assertEquals("[T][ ] Eat", task.toString());
        task.markAsDone();
        assertEquals("[T][X] Eat", task.toString());
        assertEquals(true, recursiveDeadline.isRecurring());
        assertEquals("[D][ ][Weekly] CS2103T (by: 28 Dec 2022 20:00)", recursiveDeadline.toString());
    }
}
