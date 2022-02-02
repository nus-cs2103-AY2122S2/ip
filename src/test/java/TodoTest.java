import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.tasks.Todo;

public class TodoTest {

    private LocalDateTime date;
    private Todo todo;

    @BeforeEach
    public void setUp() {
        todo = new Todo("Todo Name");
    }

    @Test
    public void getType_noInputs_success() {
        assertEquals('T', todo.getType());
    }

    @Test
    public void toString_noInputs_success() {
        assertEquals("[T][ ] Todo Name", todo.toString());
        todo.markDone();
        assertEquals("[T][X] Todo Name", todo.toString());
    }
}
