import org.junit.jupiter.api.Test;
import saitama.Storage;
import saitama.tasks.Task;
import saitama.tasks.ToDo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {

    private String FILEPATH = "data/Text.txt";
    Storage storage = new Storage(FILEPATH);
    Task toDo = new ToDo("Eat");

    @Test
    void save() {
        ArrayList<Task> test = new ArrayList<>();
        test.add(toDo);
        storage.save(test);
    }

    @Test
    void load() {
        ArrayList<Task> test = new ArrayList<>();
        test.add(toDo);
        assertEquals(test.toString(), storage.load().toString());
    }
}