import Duke.task.Deadline;
import Duke.task.Task;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    /**
     * Testing method
     */
    @Test
    public void LocalDateFormatTest() {
        Deadline d = new Deadline("return book", "2019-12-01");
        assertEquals("Dec 01 2019",
                d.getDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Testing method
     */
    @Test
    public void getStatusIconTest1() {
        Deadline d = new Deadline("return book", "2019-12-01");
        assertEquals(" ", d.getStatusIcon());
    }

    /**
     * Testing method
     */
    @Test
    public void getStatusIconTest2() {
        Deadline d = new Deadline("return book", "2019-12-01");
        d.markAsDone();
        assertEquals("X", d.getStatusIcon());
    }
}
