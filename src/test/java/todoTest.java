import tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class todoTest {
    @Test
    public void dummyTest() {
        assertEquals(2,2);
    }

    @Test
    public void todoTest(){
        Todo test = new Todo("test");
        assertEquals("T |   | test", test.toString());
    }
}
