import duke.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void createTodo_correctDescription_success(){
        assertEquals("[D][ ] make bread (by: Jan 1 2021)",
                (new Deadline("make bread", "2021-01-01")).toString());
    }
}