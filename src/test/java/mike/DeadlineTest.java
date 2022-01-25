package test.java;

import mike.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void convertToStoredTaskFormatTest() {
        assertEquals("D|false|Do homework|2022-01-02",
                new Deadline("Do homework", "2022-01-02").convertToStoredTaskFormat());
    }
}
