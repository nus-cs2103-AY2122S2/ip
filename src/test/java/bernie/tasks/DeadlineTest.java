package bernie.tasks;

import bernie.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void printDateTest() {
        LocalDate date = LocalDate.parse("2022-01-28");
        assertEquals("Jan 28 2022", new Deadline("return book", date).printDate());
    }

    @Test
    public void createDeadlineTest() {
        LocalDate date = LocalDate.parse("2022-01-28");
        assertEquals("[D][ ] return book (by: Jan 28 2022)", new Deadline("return book", date).toString());
    }

    @Test
    public void markMarkedDeadlineTest() {
        LocalDate date = LocalDate.parse("2022-01-28");
        Deadline deadline = new Deadline("return book", date);
        deadline.markDone();
        try {
            deadline.checkMark();
        } catch (InvalidArgumentException e) {
            assertEquals("Cannot mark a task already done!", e.getMessage());
        }
    }

    @Test
    public void unmarkUnmarkedDeadlineTest() {
        LocalDate date = LocalDate.parse("2022-01-28");
        Deadline deadline = new Deadline("return book", date);
        deadline.markNotDone();
        try {
            deadline.checkUnmark();
        } catch (InvalidArgumentException e) {
            assertEquals("Cannot unmark a task not done!", e.getMessage());
        }
    }

    @Test
    public void markDeadlineTest() {
        LocalDate date = LocalDate.parse("2022-01-28");
        Deadline deadline = new Deadline("return book", date);
        deadline.markDone();
        assertEquals("[D][X] return book (by: Jan 28 2022)", deadline.toString());
    }

    @Test
    public void unmarkDeadlineTest() {
        LocalDate date = LocalDate.parse("2022-01-28");
        Deadline deadline = new Deadline("return book", date);
        deadline.markDone();
        deadline.markNotDone();
        assertEquals("[D][ ] return book (by: Jan 28 2022)", deadline.toString());
    }
}
