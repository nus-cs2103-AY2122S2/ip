package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import butler.ButlerInputException;
import butler.task.Deadline;

public class DeadlineTest {
    @Test
    public void deadlineGetTaskTypeNotFormattableDateTimeReturnTaskTypeString() {
        Throwable exception = Assertions.assertThrows(ButlerInputException.class, () -> {
            Deadline deadline = new Deadline("hello", "Sat 4pm");
            Assertions.assertEquals("[D]", deadline.getTaskType());
        });

    }
}
