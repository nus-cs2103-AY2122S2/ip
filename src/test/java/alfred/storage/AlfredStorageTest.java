package alfred.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import alfred.exceptions.DuplicationException;
import alfred.task.ToDo;
import org.junit.jupiter.api.Test;

public class AlfredStorageTest {
    @Test
    public void add_tak_throwsDuplicationException() {
        ToDo todo = new ToDo("test description");
        ToDo todoDuplicate = new ToDo("test description");
        AlfredStorage storage = new AlfredStorage("./data", "./data/Alfred.txt");

        DuplicationException de = assertThrows(DuplicationException.class, () -> {
            storage.addTask(todo);
            storage.addTask(todoDuplicate);
        });
    }
}
