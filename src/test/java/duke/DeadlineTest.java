package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getDateTest() {
        Deadline d = new Deadline("Return library book", "by 2020-08-08 1800");
        assertEquals("08 Aug 2020", d.getDate());
    }

    @Test
    public void getTimeTest() {
        Deadline d = new Deadline("Return library book", "by 2020-08-08 1800");
        assertEquals("1800", d.getTime());
    }
}
