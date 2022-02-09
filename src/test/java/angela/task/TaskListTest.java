package angela.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import angela.util.Storage;

public class TaskListTest {

    @Test
    public void isFileHasTextTest() throws IOException {
        Storage storageReal = new StorageStub("data/duke.Duke.txt", "data");
        Storage storageFake = new StorageStub("test/duke.Duke.txt", "test");
        TaskList taskListReal = new TaskList(storageReal);
        TaskList taskListFake = new TaskList(storageFake);
        assertEquals(true, taskListReal.isFileHasText());
        assertEquals(false, taskListFake.isFileHasText());
    }

    @Test
    public void addTaskTest() {
        Storage storage = new StorageStub("data/duke.Duke.txt", "data");
        TaskList taskList = new TaskList(storage);
        assertEquals(0, taskList.getTotalTask());
        taskList.addTask(new Task("return book /by 2/12/2019", "D"));
        assertEquals(1, taskList.getTotalTask());
    }

    @Test
    public void deleteTaskTest() {
        Storage storage = new StorageStub("data/duke.Duke.txt", "data");
        TaskList taskList = new TaskList(storage);
        taskList.addTask(new Task("return book /by 2/12/2019", "D"));
        taskList.addTask(new Task("return book /by 2/12/2019", "D"));
        taskList.removeTask(1);
        assertEquals(1, taskList.getTotalTask());
    }

}
