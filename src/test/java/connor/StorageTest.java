package connor;

import connor.Storage;
import connor.exception.InvalidTaskFileException;
import connor.task.ToDo;
import connor.task.Deadline;
import connor.task.Event;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void testStorageStaticMethods() {
        ToDo t = new ToDo("Test");
        assertEquals("[ ] T | Test\n", Storage.taskToString(t));
        t.mark();
        assertEquals("[#] T | Test\n", Storage.taskToString(t));

        Deadline d = new Deadline("Test", "Monday");
        assertEquals("[ ] D | Test | By: Monday\n", Storage.taskToString(d));
        d.mark();
        assertEquals("[#] D | Test | By: Monday\n", Storage.taskToString(d));

        Event e = new Event("Test", "Tuesday");
        assertEquals("[ ] E | Test | At: Tuesday\n", Storage.taskToString(e));
        e.mark();
        assertEquals("[#] E | Test | At: Tuesday\n", Storage.taskToString(e));

        try {
            ToDo t2 = new ToDo("Things");
            String s = "[ ] T | Things";
            assertEquals(t2, Storage.stringToTask(s));
            t2.mark();
            String s2 = "[#] T | Things";
            assertEquals(t2, Storage.stringToTask(s2));

            LocalDate ld = LocalDate.parse("2022-10-31");
            Deadline d2 = new Deadline("Stuff", ld);
            String s3 = "[ ] D | Stuff | By: 2022-10-31\n";
            assertEquals(s3, Storage.taskToString(d2));
        } catch (InvalidTaskFileException err) {
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
