import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTask {
    @Test
    public void testEmptyInit() {
        Todo todo = new Todo();

        assertEquals("[T][ ] ", todo.toString());
    }

    @Test
    public void extractFileDataMarkTrue() {
        Todo todo = new Todo();
        todo.extractFileData("T|true|Help someone");

        assertEquals("[T][X] Help someone", todo.toString());
    }

    @Test
    public void extractFileDataMarkFalse() {
        Todo todo = new Todo();
        todo.extractFileData("T|false|Buy a new printer");

        assertEquals("[T][ ] Buy a new printer", todo.toString());
    }

    @Test
    public void saveDataMarkTrue() {
        Todo todo = new Todo("Get milk");
        todo.setIsDone(true);

        assertEquals("T|true|Get milk\n", todo.saveFileFormat());
    }

    @Test
    public void saveDataMarkFalse() {
        Todo todo = new Todo("Buy new textbooks");

        assertEquals("T|false|Buy new textbooks\n", todo.saveFileFormat());
    }
}
