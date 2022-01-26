package duke.TaskTest;

import duke.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void formatDateTest() {
        LocalDateTime test = Task.formatDateTime("2000/04/11 07:00");
        LocalDateTime actual = LocalDateTime.of(2000, 4, 11, 7, 0);
        assertEquals(test, actual);
    }
}
