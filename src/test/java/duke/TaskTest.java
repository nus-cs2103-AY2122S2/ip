package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
<<<<<<< HEAD
    public void todoTest_printMessage(){
        String expectedResult = "[T][ ]buy groceries";
=======
    public void todoTest(){
        String expectedResult = "[T][ ] buy groceries";
>>>>>>> branch-Level-9
        Task todo = new Todo("buy groceries");
        assertEquals(expectedResult, todo.toString());
    }

    @Test
<<<<<<< HEAD
    public void eventTestNoTime_printMessage(){
        String expectedResult = "[E][ ]dinner with family (at: Jan 1 2022)";
=======
    public void eventTestNoTime(){
        String expectedResult = "[E][ ] dinner with family (at: Jan 1 2022)";
>>>>>>> branch-Level-9
        Task todo = null;
        try {
            todo = new Event("dinner with family", "2022-01-01");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult, todo.toString());
    }

    @Test
<<<<<<< HEAD
    public void deadlineTestNoTime_printMessage(){
        String expectedResult = "[D][ ]Complete Tutorial (by: Jan 2 2022)";
=======
    public void deadlineTestNoTime(){
        String expectedResult = "[D][ ] Complete Tutorial (by: Jan 2 2022)";
>>>>>>> branch-Level-9
        Task todo = null;
        try {
            todo = new Deadline("Complete Tutorial", "2022-01-02");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult, todo.toString());
    }

    @Test
<<<<<<< HEAD
    public void eventTestTime_printMessage(){
        String expectedResult = "[E][ ]dinner with family (at: Jan 1 2022 16:00)";
=======
    public void eventTestTime(){
        String expectedResult = "[E][ ] dinner with family (at: Jan 1 2022 16:00)";
>>>>>>> branch-Level-9
        Task todo = null;
        try {
            todo = new Event("dinner with family", "2022-01-01 16:00");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult, todo.toString());
    }

    @Test
<<<<<<< HEAD
    public void deadlineTestTime_printMessage(){
        String expectedResult = "[D][ ]Complete Tutorial (by: Jan 2 2022 23:00)";
=======
    public void deadlineTestTime(){
        String expectedResult = "[D][ ] Complete Tutorial (by: Jan 2 2022 23:00)";
>>>>>>> branch-Level-9
        Task todo = null;
        try {
            todo = new Deadline("Complete Tutorial", "2022-01-02 23:00");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult, todo.toString());
    }
}
