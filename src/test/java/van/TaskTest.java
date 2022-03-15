package van;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void taskTester() {
        ToDo test = new ToDo("test");
        assertEquals("[T][ ] test ", test.getStatus());
    }

    @Test
    public void markTester() {
        ToDo test = new ToDo("test");
        test.setDone();
        assertEquals("[T][X] test ", test.getStatus());
        test.setUnDone();
        assertEquals("[T][ ] test ", test.getStatus());
    }

    @Test
    public void saveTester() {
        ToDo test = new ToDo("test");
        test.setDone();
        assertEquals("T|1|test| ", test.saveStatus());
    }
}
