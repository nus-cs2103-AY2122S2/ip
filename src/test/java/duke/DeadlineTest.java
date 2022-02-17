package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void testInstantiateDeadline() {
        Deadline deadline = new Deadline("test", true, LocalDate.parse("2020-12-12"));
        assertEquals("[D][x] test (by: 2020-12-12", deadline.toString());
    }

}
