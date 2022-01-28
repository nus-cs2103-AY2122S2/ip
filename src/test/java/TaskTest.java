import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import taskmaster.task.DeadlineTask;
import taskmaster.task.EventTask;
import taskmaster.task.TodoTask;
import taskmaster.util.TaskList;



public class TaskTest {
    private TaskList taskList = new TaskList();

    @Test
    @DisplayName("Test Display String for Todo Tasks")
    public void testToStringForTodo() {
        TodoTask task = new TodoTask("Deja Vu");
        assertEquals("[T][] Deja Vu", task.toString());
    }

    @Test
    @DisplayName("Test Display String for Event Tasks")
    public void testToStringForEvent() {
        LocalDateTime checkDateAndTime = LocalDateTime.parse("9/9/1990 0909",
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        EventTask task = new EventTask("Running in the 90s", checkDateAndTime);
        assertEquals("[E][] Running in the 90s (at: 9 Sep 1990, 9:09AM)", task.toString());

    }

    @Test
    @DisplayName("Test Display String for Deadline Tasks")
    public void testToStringForDeadline() {
        LocalDateTime checkDateAndTime = LocalDateTime.parse("10/10/2000 2010",
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        DeadlineTask task = new DeadlineTask("GAS GAS GAS", checkDateAndTime);
        assertEquals("[D][] GAS GAS GAS (by: 10 Oct 2000, 8:10PM)", task.toString());

    }

    @Test
    @DisplayName("Test save format for Deadline Task")
    public void testSaveFormatDeadlineTask() {
        LocalDateTime checkDateAndTime = LocalDateTime.parse("10/10/2000 2010",
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        DeadlineTask task = new DeadlineTask("GAS GAS GAS", checkDateAndTime);
        assertEquals("D | 0 | GAS GAS GAS | 10/10/2000 2010", task.saveToFileFormat());

    }

    @Test
    @DisplayName("Test save format for Event Task")
    public void testSaveFormatEventTask() {
        LocalDateTime checkDateAndTime = LocalDateTime.parse("9/9/1990 0909",
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        EventTask task = new EventTask("Running in the 90s", checkDateAndTime);
        assertEquals("E | 0 | Running in the 90s | 9/9/1990 0909", task.saveToFileFormat());

    }


    @Test
    @DisplayName("Test save format for Todo Task")
    public void testSaveFormatTodoTask() {
        TodoTask task = new TodoTask("Deja Vu");
        assertEquals("T | 0 | Deja Vu", task.saveToFileFormat());
    }
}
