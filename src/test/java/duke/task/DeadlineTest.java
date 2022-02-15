package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toString_deadline_correctStringRepresentationReturned() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse("01-01-2022 00:00", formatter);

        Deadline deadline = new Deadline("test", true, localDateTime);

        assertEquals(deadline.toString(), "[D][X] test (by: 00:00, Jan 01 2022)");
    }

    @Test
    public void toData_deadline_correctDataRepresentationReturned() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse("01-01-2022 00:00", formatter);

        Deadline deadline = new Deadline("test", true, localDateTime);

        assertEquals(deadline.toData(), "D | true | test | 2022-01-01T00:00");
    }
}
