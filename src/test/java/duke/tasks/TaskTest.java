package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task task = new Task("testing task");

    @Test
    void toStringTest() {

        String expected = "[ ] testing task";
        assertEquals(expected, task.toString());
    }

    @Test
    void markAsDoneTest() {
        task.markAsDone();
        String expectedMarked = "[X] testing task";
        assertEquals(expectedMarked, task.toString());
    }

    @Test
    void markAsNotDoneTest() {
        task.markAsDone();
        task.markAsNotDone();
        String expectedUnmarked = "[ ] testing task";
        assertEquals(expectedUnmarked, task.toString());
    }

    @Test
    void toSaveDataTest() {
        task.markAsDone();
        String expectedMarked = "1|testing task";
        assertEquals(expectedMarked, task.toSaveData());
        task.markAsNotDone();
        String expectedUnmarked = "0|testing task";
        assertEquals(expectedUnmarked, task.toSaveData());
    }
}
