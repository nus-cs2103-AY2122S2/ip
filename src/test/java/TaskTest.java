import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import saitama.tags.RecurFrequency;
import saitama.tasks.Deadline;
import saitama.tasks.Task;
import saitama.tasks.ToDo;

class TaskTest {

    private String tick = "\u2713";
    private String cross = "\u2718";
    private Task task = new ToDo("Eat", null);
    private Task recursiveDeadline = new Deadline("CS2103T", LocalDateTime.of(2052, 12, 28, 20, 0),
            true, RecurFrequency.WEEKLY, LocalDate.of(2000, 12, 20));

    @Test
    void testToDo() {
        assertEquals(String.format("[T][%s] Eat", cross), task.toString());
        task.markAsDone();
        assertEquals(String.format("[T][%s] Eat", tick), task.toString());
    }

    @Test
    void testRecursiveDeadline() {
        assertEquals(true, recursiveDeadline.isRecurring());
        assertEquals(String.format("[D][%s](Weekly) CS2103T (by: 28 Dec 2052 20:00)", cross),
                recursiveDeadline.toString());
    }
}
