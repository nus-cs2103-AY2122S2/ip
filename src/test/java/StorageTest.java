import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import saitama.Storage;
import saitama.tasks.Task;
import saitama.tasks.ToDo;



class StorageTest {

    private String filePath = "data/Text.txt";
    private Storage storage = new Storage(filePath);
    private Task toDo = new ToDo("Eat");
    private ArrayList<Task> test = new ArrayList<>();

    @Test
    void save() {
        test.add(toDo);
        storage.save(test);
    }

    @Test
    void load() {
        test.add(toDo);
        assertEquals(test.toString(), storage.load().toString());
    }
}
