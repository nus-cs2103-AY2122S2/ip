import duke.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void createTodo_correctDescription_success(){
        assertEquals("[E][ ] make bread (at: Jan 1 2021)",
                (new Event("make bread", "2021-01-01")).toString());
    }
}