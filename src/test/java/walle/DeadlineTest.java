package walle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest_deadlineTask_success() {
        Deadline deadline = new Deadline("eat dinner", "27-01-2022 2400",0, true);
        String comp = "1. [D][ ] eat dinner (by: 28 Jan 2022 12:00 AM)\n";
        assertEquals(comp, deadline.toString());
    }

}
