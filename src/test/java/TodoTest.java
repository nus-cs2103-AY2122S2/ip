import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.Todo;

public class TodoTest {
    @Test
    public void toStringTest() {
        Todo todo = new Todo("Wash clothes");

        assertEquals("[T][ ] Wash Clothes", todo.toString());
    }
}
