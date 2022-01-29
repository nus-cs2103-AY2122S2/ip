package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import echo.task.DeadlineTask;


public class DeadlineTaskTest {

    @Test
    public void testSaveFormat() {
        LocalDateTime localDateTime = LocalDateTime.parse("2022-1-1 1800",
                DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
        String desc = "return book";
        DeadlineTask deadlineTask = new DeadlineTask(desc, localDateTime);
        assertEquals("D | 0 | return book | 2022-1-1 1800", deadlineTask.saveFormat());
    }

    @Test
    public void testToString() {
        LocalDateTime localDateTime = LocalDateTime.parse("2022-1-1 1800",
                DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
        String desc = "return book";
        DeadlineTask deadlineTask = new DeadlineTask(desc, localDateTime);
        assertEquals("[D][ ] return book (by: Jan 01 2022, 6:00PM)", deadlineTask.toString());
    }

}
