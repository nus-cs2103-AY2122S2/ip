package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void toString_markTask_success() {
        Deadline deadline = new Deadline("assignment", true, LocalDate.of(2022, 11, 6),
                LocalTime.of(23, 59));

        assertEquals("[D][X] assignment (by: 6 Nov 2022 11:59PM)", deadline.toString());

    }

    @Test
    void toData() {
        Deadline deadline = new Deadline("assignment", true, LocalDate.of(2022, 11, 6),
                LocalTime.of(23, 59));

        assertEquals("D|1|assignment|2022-11-06|23:59", deadline.toData());
    }
}
