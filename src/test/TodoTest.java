
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void markAsDone_markedCorrectly() {
        Todo todo = new Todo("Return book");
        Todo doneTodo = todo.markAsDone();
        assertTrue(doneTodo.isDone());
    }

}