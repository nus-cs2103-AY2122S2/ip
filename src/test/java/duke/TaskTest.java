package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * test the output when tasks are being added
 */
public class TaskTest {
    /**
     * test adding todo task
     */
    @Test
    public void todoTest() {
        String expectedResult = "[T][ ] buy groceries";
        Task todo = new Todo("buy groceries");
        assertEquals(expectedResult, todo.toString());
    }

    /**
     * test adding an event without time
     */
    @Test
    public void eventTestNoTime() {
        String expectedResult = "[E][ ] dinner with family (at: Jan 1 2022)";
        Task todo = null;
        try {
            todo = new Event("dinner with family", "2022-01-01");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult, todo.toString());
    }

    /**
     * test adding a deadline without time
     */
    @Test
    public void deadlineTestNoTime() {
        String expectedResult = "[D][ ] Complete Tutorial (by: Jan 2 2022)";
        Task todo = null;
        try {
            todo = new Deadline("Complete Tutorial", "2022-01-02");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult, todo.toString());
    }

    /**
     * test adding an event with time
     */
    @Test
    public void eventTestTime() {
        String expectedResult = "[E][ ] dinner with family (at: Jan 1 2022 16:00)";
        Task todo = null;
        try {
            todo = new Event("dinner with family", "2022-01-01 16:00");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult, todo.toString());
    }

    /**
     * test adding deadline without time
     */
    @Test
    public void deadlineTestTime() {
        String expectedResult = "[D][ ] Complete Tutorial (by: Jan 2 2022 23:00)";
        Task todo = null;
        try {
            todo = new Deadline("Complete Tutorial", "2022-01-02 23:00");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult, todo.toString());
    }
}
