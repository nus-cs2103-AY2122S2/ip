package duke;

import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void successfulDeadlineTest() {
        Deadline test = new Deadline("wassup bro", LocalDate.parse("2019-12-01"), "1800");
        assertEquals("[D][ ] wassup bro (by: 01 Dec 2019 1800)", test.toString());
    }
}
