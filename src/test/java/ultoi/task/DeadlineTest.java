package ultoi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void toInputString_normalInput_success() throws Exception {
        Deadline deadline = new Deadline("sample deadline", "2020-02-02 0202");
        assertEquals("deadline sample deadline /by 2020-02-02 0202", deadline.toInputString());
    }
}
