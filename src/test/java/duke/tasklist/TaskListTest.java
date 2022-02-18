package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.storage.StorageStub;
import duke.task.Tasks;
import duke.task.Todos;

// Format of testing -> WhatIsTested_descriptionOfTestInputs_ExpectedOutcome
// Testing .assertEquals(Expected, actual)
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
