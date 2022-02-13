import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.data.exception.IllegalValueException;
import org.junit.jupiter.api.Test;

import duke.data.task.TodoTask;

/**
 * Tests the todo task.
 */
public class TodoTaskTest {
    /**
     * Test that the todo task can be constructed correctly,
     * and its methods work correctly.
     */
    @Test
    public void todoTask_constructor() throws IllegalValueException {
        String todoTag = "Meal";
        String todoDesc = "Eat chicken";
        String todoDone = "[T][X] Eat chicken <Meal>";
        String todoUndone = "[T][ ] Eat chicken <Meal>";
        TodoTask task = new TodoTask(todoDesc, todoTag);

        // instantiates with correct description and is undone
        assertEquals(task.toString(), todoUndone);

        // can get correct description
        assertEquals(task.getDescription(), todoDesc);

        // can be marked as done
        task.markDone();
        assertEquals(task.toString(), todoDone);

        // can be marked as undone
        task.markUndone();
        assertEquals(task.toString(), todoUndone);

    }
}
