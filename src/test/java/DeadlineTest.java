import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DeadlineTest {

    @Test
    public void inputStringTest() {
        assertEquals("[D][ ] finish assignment (by: tomorrow)",
                new Deadline("finish assignment", "tomorrow").toString());
    }

    @Test
    public void inputLocalDateTest() {
        assertEquals("[D][ ] finish assignment (by: Feb-28-2022)",
                new Deadline("finish assignment", LocalDate.of(2022, 2, 28)).toString());
    }



}
