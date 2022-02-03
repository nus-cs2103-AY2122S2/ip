package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest_deadlineTask_success(){
        Deadline deadline = new Deadline("eat dinner", 0, "2022-01-27 2400", true);
        String comp = "1. [D][ ] eat dinner (by: Jan 28 2022 12:00 AM)\n";
        assertEquals(comp, deadline.toString());
    }

}
