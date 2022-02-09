package connor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import connor.exception.ConnorException;
import connor.exception.InvalidTaskFileException;
import connor.task.Deadline;
import connor.task.Event;
import connor.task.ToDo;

public class StorageTest {
    @Test
    public void testStorageStaticMethods() {
        try {
            ToDo t = new ToDo("Test");
            assertEquals("[ ] T | Test\n", Storage.taskToString(t));
            t.mark();
            assertEquals("[#] T | Test\n", Storage.taskToString(t));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse("19-02-2022 14:30", formatter);
            Deadline d = new Deadline("Test", dateTime);
            assertEquals("[ ] D | Test | By: 19-02-2022 14:30\n", Storage.taskToString(d));
            d.mark();
            assertEquals("[#] D | Test | By: 19-02-2022 14:30\n", Storage.taskToString(d));

            LocalDateTime dateTime2 = LocalDateTime.parse("31-03-2022 23:30", formatter);
            Event e = new Event("Test", dateTime2);
            assertEquals("[ ] E | Test | At: 31-03-2022 23:30\n", Storage.taskToString(e));
            e.mark();
            assertEquals("[#] E | Test | At: 31-03-2022 23:30\n", Storage.taskToString(e));

            ToDo t2 = new ToDo("Things");
            String s = "[ ] T | Things";
            assertEquals(t2, Storage.stringToTask(s));
            t2.mark();
            String s2 = "[#] T | Things";
            assertEquals(t2, Storage.stringToTask(s2));

        } catch (InvalidTaskFileException err) {
            fail();
        } catch (ConnorException err) {
            fail();
        }

        String s4 = "[ ] X | Blah";
        try {
            assertEquals("", Storage.stringToTask(s4));
        } catch (InvalidTaskFileException ex) {
            assertEquals("This task file is invalid!", ex.getMessage());
        }

        String s5 = "[ ] D | Invalid Number of delimiters";
        assertThrows(IndexOutOfBoundsException.class, () -> Storage.stringToTask(s5));

    }
}
