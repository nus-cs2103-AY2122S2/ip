package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void toStringAndSaveFormat_toDoTask() {
        Task toDoTask = new ToDo("Test ToDo Task");
        assertEquals("[T] [   ] Test ToDo Task", toDoTask.toString());
        assertEquals("T | 0 | Test ToDo Task", toDoTask.saveFormat());
    }

    @Test
    public void toStringAndSaveFormat_deadlineTask() {
        Task deadlineTask = new Deadline("Test Deadline Task", "d/2022-01-30 t/2359");
        assertEquals("[D] [   ] Test Deadline Task (by: Jan 30 2022, 11:59 PM)", deadlineTask.toString());
        assertEquals("D | 0 | Test Deadline Task | d/2022-01-30 t/2359", deadlineTask.saveFormat());
    }

    @Test
    public void toStringAndSaveFormat_eventTask() {
        Task eventTask = new Event("Test Event Task", "d/2022-01-31 t/1900-2200");
        assertEquals("[E] [   ] Test Event Task (at: Jan 31 2022, 07:00 PM to 10:00 PM)", eventTask.toString());
        assertEquals("E | 0 | Test Event Task | d/2022-01-31 t/1900-2200", eventTask.saveFormat());
    }
}
