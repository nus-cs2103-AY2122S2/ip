package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task test = new ToDo("Test", false);

    /**
     * Tests the toggleMark method adopted by all Tasks subclasses
     */
    @Test
    public void toggleMarkTest() {

        assertEquals("To do - Test", test.toString(), "Failed creation");

        test.toggleMark();

        assertEquals("!!!DONE!!! To do - Test", test.toString(), "Failed toggle");
    }
}
