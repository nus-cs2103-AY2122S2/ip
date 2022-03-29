import duke.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void getTodoDescription() {
        Todo todoTask = new Todo("Wash the dishes");
        assertEquals("Wash the dishes", todoTask.getDescription());
    }

    @Test
    public void checkTaskStatus() {
        Todo todoTask1 = new Todo("Watch Gotham latest Season on Netflix");
        Todo todoTask2 = new Todo("Start reading up on NFTs");
        todoTask1.setDone();
        todoTask2.setDone();
        todoTask2.setUndone();
        assertEquals("[T][X]", todoTask1.getStatusIcon());
        assertEquals("[T][ ]", todoTask2.getStatusIcon());
    }
}