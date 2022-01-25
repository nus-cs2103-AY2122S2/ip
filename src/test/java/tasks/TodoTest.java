package tasks;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void toStringTest() {
        Todo todoTask = new Todo("test");
        String actual = todoTask.toString();
        String expected = "[T][ ] test";
        assertEquals(expected, actual);
    }
}
