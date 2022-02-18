package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.storage.StorageStub;

/**
 * A class that is used to test the methods within the TaskList class.
 */
public class TaskListTest {

    @Test
    void fileContentCounter_writtenCorrectly_success () {
        TaskList printTaskList = new TaskList(new StorageStub().preloadTaskList());
        assertEquals (3 , printTaskList.fileContentCounterZeroed());
    }

    @Test
    void arrayCounter_writtenCorrectly_success () {
        TaskList printTaskList = new TaskList(new StorageStub().preloadTaskList());
        assertEquals(6, printTaskList.arrayCounter(new int[]{5, 6, 7, 8, 9, 10}));
    }
}
