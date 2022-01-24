package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void todoTest(){
        String expectedResult = "[T][ ] buy groceries";
        Task todo = new Todo("buy groceries");
        assertEquals(expectedResult, todo.toString());
    }

    @Test
    public void eventTestNoTime(){
        String expectedResult = "[E][ ] dinner with family (at: Jan 1 2022)";
        Task todo = null;
        try {
            todo = new Event("dinner with family", "2022-01-01");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult, todo.toString());
    }

    @Test
    public void deadlineTestNoTime(){
        String expectedResult = "[D][ ] Complete Tutorial (by: Jan 2 2022)";
        Task todo = null;
        try {
            todo = new Deadline("Complete Tutorial", "2022-01-02");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult, todo.toString());
    }

    @Test
    public void eventTestTime(){
        String expectedResult = "[E][ ] dinner with family (at: Jan 1 2022 16:00)";
        Task todo = null;
        try {
            todo = new Event("dinner with family", "2022-01-01 16:00");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult, todo.toString());
    }

    @Test
    public void deadlineTestTime(){
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
