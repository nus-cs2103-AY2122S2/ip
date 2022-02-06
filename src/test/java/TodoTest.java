import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.info.task.Todo;







public class TodoTest {

    /**
     * Tests the constructor for creating a Todo with a custom description, and checks if it has the type todo"
     */
    @Test
    public void constructorTest() {
        Todo testTodo = new Todo("Finish CS2103T Assignment", false);
        assertEquals(testTodo.getAction(), "Finish CS2103T Assignment");
        assertEquals(testTodo.getType(), "todo");
    }

    /**
     * Tests if the toString format is correct for the Todo created
     */
    @Test
    public void stringTest() {
        Todo testTodo = new Todo("Finish CS2103T Assignment", false);
        assertEquals(testTodo.toString(), "[T][ ] Finish CS2103T Assignment");
    }
}
