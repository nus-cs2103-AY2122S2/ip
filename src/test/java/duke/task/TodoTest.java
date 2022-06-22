package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void toStringTest() {
        Task testTask = new Todo("read book");
        assertEquals("[T][ ] read book", testTask.toString());
    }

    @Test
    public void markAsDoneTest() {
        Task testTask = new Todo("read book");
        testTask.markAsDone();
        assertEquals("[T][X] read book", testTask.toString());
    }

}