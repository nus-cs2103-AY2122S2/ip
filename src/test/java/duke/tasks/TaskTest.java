package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    @DisplayName("Status Icon check is okay.")
    public void checkStatus() {
        Task exampleTask = new Todo("hello world");
        exampleTask.markAsDone();
        String expected = "X";
        String actual = exampleTask.getStatusIcon();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("String representation of Task is fine.")
    public void checkString() {
        Task exampleTask = new Todo("study hard");
        String expected = "[T][ ] study hard";
        String actual = exampleTask.toString();
        assertEquals(expected, actual);
    }

}
