package li.zhongfu.cs2103.chatbot.types;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import li.zhongfu.cs2103.chatbot.exceptions.StorageException;
import li.zhongfu.cs2103.chatbot.types.tasks.Deadline;
import li.zhongfu.cs2103.chatbot.types.tasks.Event;
import li.zhongfu.cs2103.chatbot.types.tasks.Task;
import li.zhongfu.cs2103.chatbot.types.tasks.ToDo;

public class StorageTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private Storage storage;

    @Before
    public void setup() throws IOException {
        storage = new Storage(folder.newFile().toString());
    }

    @Test
    public void load_emptyFile() throws FileNotFoundException, StorageException {
        List<Object> objects = storage.load();
        assertEquals(0, objects.size());
    }

    @Test
    public void saveAndLoad_whitelistedTypes() throws IOException, StorageException {
        List<? super Task> objects = new ArrayList<>();
        objects.add(new Deadline("random deadline", LocalDateTime.now().plusDays(4)));
        objects.add(new Event("random event", LocalDateTime.now().plusMinutes(30)));
        objects.add(new ToDo("hello world"));

        storage.save(objects);
        List<Object> objects2 = storage.load();
        assertTrue(objects.size() == objects2.size());
    }

    @Test
    public void saveAndLoad_unwhitelistedTypes() throws IOException {
        List<? super Object> objects = new ArrayList<>();
        objects.add(new TestClass());
        storage.save(objects);
        assertThrows(StorageException.class, () -> storage.load());
    }
}

class TestClass implements Serializable {
}