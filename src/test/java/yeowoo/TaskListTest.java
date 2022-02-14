package yeowoo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.TaskList;

public class TaskListTest {
    @Test
    public void fileNotFoundTest() throws YeowooException {
        TaskList tList = new TaskList(new Storage("Test"), new StorageStub().load());
        assertEquals(0, tList.getTasks().size());
    }
}

