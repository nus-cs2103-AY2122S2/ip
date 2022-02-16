import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Todo;

public class TodoTest {
    @Test
    public void testEmptyInit() {
        Todo todo = new Todo();

        assertEquals("[T][ ] ", todo.toString());
    }

    @Test
    public void extractFileDataMarkTrue() throws DukeException {
        Todo todo = new Todo();
        todo.extractDataFromLine("T|true|Help someone");

        assertEquals("[T][X] Help someone", todo.toString());
    }

    @Test
    public void extractFileDataMarkFalse() throws DukeException {
        Todo todo = new Todo();
        todo.extractDataFromLine("T|false|Buy a new printer");

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
