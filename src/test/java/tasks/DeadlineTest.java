package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testDeadline() {
        Deadline deadline1 = new Deadline("read book", "Aug 22 2022");
        String deadline = deadline1.toString();
        assertEquals(deadline, "[D] [ ] read book (by: Aug 22 2022)");
    }
}
