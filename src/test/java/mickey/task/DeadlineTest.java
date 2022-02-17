package mickey.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        assertEquals("[D][ ] test (by: Sep 18 2022 05:30 PM)", new Deadline("test", "2022-09-18 1730").toString());
    }
}
