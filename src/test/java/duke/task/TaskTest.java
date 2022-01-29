package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toStringAndSaveFormat_toDoTask() {
        Task toDoTask = new ToDo("Test ToDo Task");
        assertEquals("[T] [ ] Test ToDo Task", toDoTask.toString());
        assertEquals("T | 0 | Test ToDo Task", toDoTask.saveFormat());
    }

    @Test
    public void toStringAndSaveFormat_deadlineTask() {
        Task deadlineTask = new Deadline("Test Deadline Task", "2022-01-30 2359");
        assertEquals("[D] [ ] Test Deadline Task (by: Jan 30 2022, 11:59 PM)", deadlineTask.toString());
        assertEquals("D | 0 | Test Deadline Task | 2022-01-30 2359", deadlineTask.saveFormat());
    }

    @Test
    public void toStringAndSaveFormat_eventTask() {
        Task eventTask = new Event("Test Event Task", "2022-01-31 7-10pm");
        assertEquals("[E] [ ] Test Event Task (at: Jan 31 2022, 7-10pm)", eventTask.toString());
        assertEquals("E | 0 | Test Event Task | 2022-01-31 7-10pm", eventTask.saveFormat());
    }
}
