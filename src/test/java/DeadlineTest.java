import duke.tasks.Deadline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private LocalDateTime date;
    private Deadline deadline;

    @BeforeEach
    public void setUp(){
        date = LocalDateTime.parse("2007-12-03T10:15:30");
        deadline = new Deadline("Deadline Name", date);
    }

    @Test
    public void getType_noInputs_success() {
        assertEquals('D', deadline.getType());
    }

    @Test
    public void toString_noInputs_success() {
        assertEquals("[D][ ] Deadline Name (by: 3 Dec 2007, 10:15AM)", deadline.toString());
        deadline.markDone();
        assertEquals("[D][X] Deadline Name (by: 3 Dec 2007, 10:15AM)", deadline.toString());
    }
}
