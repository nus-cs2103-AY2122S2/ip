package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TaskTest {

    @Test
    public void toFileTextTest() {
        Task task1 = new Task("return book /by 2/12/2019", "D");
        Task task2 = new Task("project meeting /at 12/4/2020", "E");
        Task task3 = new Task("borrow book", "T");
        assertEquals("| D | 0 | return book | by 2/12/2019 |", task1.createTextDatabase());
        assertEquals("| E | 0 | project meeting | at 12/4/2020 |", task2.createTextDatabase());
        assertEquals("| T | 0 | borrow book |", task3.createTextDatabase());
    }

    @Test
    public void getTypeTest() {
        Task task1 = new Task("return book /by 2/12/2019", "D");
        Task task2 = new Task("project meeting /at 12/4/2020", "E");
        Task task3 = new Task("borrow book", "T");
        assertEquals("D", task1.getType());
        assertEquals("E", task2.getType());
        assertEquals("T", task3.getType());
    }

    @Test
    public void getTimeTest() {
        Task task1 = new Task("return book /by 2/12/2019", "D");
        Task task2 = new Task("project meeting /at 12/4/2020", "E");
        Task task3 = new Task("borrow book", "T");
        LocalDate date1 = LocalDate.parse("2/12/2019", DateTimeFormatter.ofPattern("d/M/yyyy"));
        LocalDate date2 = LocalDate.parse("12/4/2020", DateTimeFormatter.ofPattern("d/M/yyyy"));
        assertEquals(date1, task1.getTime());
        assertEquals(date2, task2.getTime());
        assertNull(task3.getTime());
    }
    @Test
    public void toStringTest() {
        Task task1 = new Task("return book /by 2/12/2019", "D");
        Task task2 = new Task("project meeting /at 12/4/2020", "E");
        Task task3 = new Task("borrow book", "T");
        assertEquals("[D][ ] return book (by: Dec 2 2019)", task1.toString());
        assertEquals("[E][ ] project meeting (at: Apr 12 2020)", task2.toString());
        assertEquals("[T][ ] borrow book", task3.toString());
    }
}
