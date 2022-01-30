package apollo.tasks;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test @Order(4)
    void getStatus() {
        Task task = new Task("task", Task.Type.TODO);
        assertEquals(" ", task.getStatus());

        task.markAs(true);
        assertEquals("X", task.getStatus());

        task.markAs(true);
        assertEquals("X", task.getStatus());
    }

    @Test @Order(3)
    void markAs() {
        Task task = new Task("task", Task.Type.TODO);

        task.markAs(true);
        String markExpected = "[T][X] task";
        assertEquals(markExpected, task.toString());

        task.markAs(false);
        String unmarkExpected = "[T][ ] task";
        assertEquals(unmarkExpected, task.toString());

        task.markAs(false);
        assertEquals(unmarkExpected, task.toString());
    }

    @Test @Order(2)
    void testToString_Task() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 20, 0, 0);

        Task todo = new Task("task", Task.Type.TODO);
        String todoExpected = "[T][ ] task";
        assertEquals(todoExpected, todo.toString());

        Task deadline = new Task("task", Task.Type.DEADLINE);
        String deadlineExpected = "[D][ ] task";
        assertEquals(deadlineExpected, deadline.toString());

        Task event = new Task("task", Task.Type.EVENT);
        String eventExpected = "[E][ ] task";
        assertEquals(eventExpected, event.toString());
    }

    @Test @Order(1)
    void testToString_enumType() {
        assertEquals(Task.Type.TODO.toString(), "T");
        assertEquals(Task.Type.DEADLINE.toString(), "D");
        assertEquals(Task.Type.EVENT.toString(), "E");
    }
}
