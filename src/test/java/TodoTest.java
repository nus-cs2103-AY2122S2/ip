
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void markAsDone_markedCorrectly() {
        Todo todo = new Todo("Return book");
         todo.markAsDone();
        assertTrue(todo.isDone());
    }

    public static class TaskTest {


        @Test
        public void testName() {
            assertEquals("Return book", new Task("Return book").getName());
        }

        @Test
        public void testDone() {
            Task sub3 = new Task("Return book");
            sub3.markAsDone();
            assertTrue(sub3.isDone());
        }



        @Test
        public void testStatus() {
            assertEquals("[ ]Return book", new Task("Return book").toString());
            Task sub6 = new Task("Return book");
            sub6.markAsDone();
            assertEquals("[X]Return book", sub6.toString());
        }
    }
}