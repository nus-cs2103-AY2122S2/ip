package bob;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void taskListTest() {
        bob.TaskList tasks = new TaskList();
        tasks.add(new ToDo("this"));
        assertEquals(tasks.size(), 1);
    }

}
