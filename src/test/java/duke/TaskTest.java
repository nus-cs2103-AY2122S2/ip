package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest{
    @Test
    public void containsTest() {
        Task t = new Task("hello hello", false);
        assertEquals(false, t.isDone);
    }
}