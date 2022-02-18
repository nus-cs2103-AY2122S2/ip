package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void getDetails_newDeadlineCreated_returnStringArray() {
        Deadline deadline = new Deadline("Description", "time");
        String[] expected = new String[]{"DEADLINE", "0", "Description", "time"};
        String[] actual = deadline.getDetails();
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }
}
