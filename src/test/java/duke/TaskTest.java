package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void markAsDone_CallOnTask_ChangesIsCompleteToTrue() {
        Task t = new Task("INPUT");
        t.markAsDone();
        assertEquals(t.isComplete, true);
    }

    @Test
    public void unmarkAsDone_CallOnTask_ChangesIsCompleteToFalse() {
        Task t = new Task("INPUT");
        t.unmarkAsDone();
        assertEquals(t.isComplete, false);
    }
}
