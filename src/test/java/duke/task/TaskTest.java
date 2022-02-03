package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testTodoSaveDataFormat() {
        Todo todo = new Todo("Go for a run");
        todo.markAsDone();
        assertEquals(todo.toSaveDataFormat(), "T|1|Go for a run\n");
    }
    @Test
    public void testDeadlineSaveDataFormat() {
        Deadline deadline = new Deadline("Finish reading book", "2022-01-31");
        deadline.markAsDone();
        deadline.markAsNotDone();
        assertEquals(deadline.toSaveDataFormat(), "D|0|Finish reading book|Jan 31 2022\n");
    }
    @Test
    public void testEventSaveDataFormat() {
        Event event = new Event("IS4241 Tutorial", "26 Jan 8pm");
        event.markAsDone();
        assertEquals(event.toSaveDataFormat(), "E|1|IS4241 Tutorial|26 Jan 8pm\n");
    }

}
