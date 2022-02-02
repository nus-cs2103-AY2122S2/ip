package meep.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TaskTest {
    private ListTask tasks;
    private Deadline deadline;
    private Event event;
    private ToDo todo;

    @BeforeEach
    public void setUp() {
        LocalDateTime dateTime = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);

        deadline = new Deadline("test1", dateTime);
        event = new Event("test2", dateTime);
        todo = new ToDo("test3");
        tasks = new ListTask();
        tasks.addTask(deadline);
        tasks.addTask(event);
        tasks.addTask(todo);
    }

    @Test
    public void deleteTask_indexOutOfBounds_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tasks.deleteTask(-1);
        }, "InvalidInputException was expected");
    }

    @Test
    public void getTask_indexOutOfBounds_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tasks.get(-1);
        }, "InvalidInputException was expected");
    }

    @Test
    public void generateTaskList_withoutDate_success() {
        String expected = "\n     1.  [D][ ] test1 (by: Jul 29 2015 19:30)\n"
                + "     2.  [E][ ] test2 (on: Jul 29 2015 19:30)\n"
                + "     3.  [T][ ] test3\n";

        assertEquals(expected, tasks.generateTaskList());
    }

    @Test
    public void generateTaskList_withDateGiven_success() {
        String expected = "\n     1.  [D][ ] test1 (by: Jul 29 2015 19:30)\n"
                + "     2.  [E][ ] test2 (on: Jul 29 2015 19:30)\n";


        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime formattedDate = LocalDateTime.parse("29/08/2015 18:00", format);

        assertEquals(expected, tasks.generateTaskList(formattedDate));
    }
}
