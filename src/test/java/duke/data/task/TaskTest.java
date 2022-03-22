package duke.data.task;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void markTest() {
        Task task = new Task("Test");

        task.markAsDone();
        task.unmarkAsDone();
        assertEquals(new Task("Test"), task);
    }

    @Test
    public void toStringTest() {
        Task task = new Task("Test");

        // test case 1
        assertEquals("[ ] Test", task.toString());

        // test case 2
        task.markAsDone();
        assertEquals("[X] Test", task.toString());
    }

    @Test
    public void getWordsInDescriptionTest() {
        Task task = new Task("Hello world");

        assertEquals(Arrays.asList("Hello", "world"), task.getWordsInDescription());
    }
}
