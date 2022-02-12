package duke;
import org.junit.jupiter.api.Test;
import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void fileNotFoundTest() throws DukeException {
        TaskList tList = new TaskList(new Storage("Test"), new StorageStub().load());
        assertEquals(0, tList.getTasks().size());
    }
}

