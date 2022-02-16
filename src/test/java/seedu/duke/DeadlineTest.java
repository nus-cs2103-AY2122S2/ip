package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import seedu.duke.task.Deadline;

public class DeadlineTest {
    private Deadline deadline;

    @BeforeEach
    void setUp() {
        TemporalAccessor ta = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").parse("2018-09-12 18:14");
        LocalDateTime date = LocalDateTime.from(ta);
        deadline = new Deadline("testing", date);
    }

    @Test
    @DisplayName("Task type should be D")
    void testGetTaskType() {
        assertEquals("D",
                deadline.getTaskType(),
                "Task type should return D");
    }

    @Test
    @DisplayName("getTaskName() should return testing")
    void testToString() {
        assertEquals("testing",
                deadline.getTaskName());
    }
}
