package pyke.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DeadlineTest {
    private final LocalDate mockDate = LocalDate.parse("2002-06-25");

    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null, mockDate));
    }

    @Test
    public void constructor_nullDate_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline("DESCRIPTION", null));
    }
}
