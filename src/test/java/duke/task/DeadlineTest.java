package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    void constructor_invalidDateFormat_exceptionThrown() {
        String deadlineName = "Test Deadline";
        String invalidDateString = "01-01-2020";
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline(deadlineName, invalidDateString) ;
        });
    }

    @Test
    void getDescription_sampleDeadline_matchExpected() {
        Deadline deadline = new Deadline("Test Deadline", "01/01/2020");
        String expectedDescription = "[D][ ] Test Deadline (by: 01 Jan)";
        assertEquals(expectedDescription, deadline.getDescription());
    }

    @Test
    void encodeTaskData_sampleDeadline_matchExpected() {
        Deadline deadline = new Deadline("Test Deadline", "01/01/2020");
        String expectedEncoding = "D,Test Deadline,N,01/01/2020";
        assertEquals(expectedEncoding, deadline.encodeTaskData());
    }
}
