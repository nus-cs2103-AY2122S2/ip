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
    void addsTask_writtenCorrectly_success () {
        try {
            Storage crudDatabase = new StorageStub(
                    "./data/DukeDatabaseStub.txt");
            ArrayList<Tasks> oldStorageList = crudDatabase.load();
            int oldStorageListSize = oldStorageList.size();

            TaskList addTaskList = new TaskList(oldStorageList);
            Tasks addTask = new Todos("Testing Addition");
            addTaskList.addsTask(addTask, crudDatabase);
            ArrayList<Tasks> newStorageList = crudDatabase.load();
            assertEquals(oldStorageListSize + 1, newStorageList.size());
        } catch (FileNotFoundException err) {
            fail("Database loading failed. Test failed testing addTask.");
        }
    }

    @Test
    void deletesTask_writtenCorrectly_success() {
        try {
            Storage crudDatabase = new StorageStub(
                    "./src/test/java/duke/data/DukeDatabaseStub.txt");
            ArrayList<Tasks> oldStorageList = crudDatabase.load();
            if (oldStorageList.size() > 0) {
                TaskList addTaskList = new TaskList(oldStorageList);
                Tasks addTask = new Todos("Testing Addition");
                addTaskList.deletesTask(0, crudDatabase);
                ArrayList<Tasks> newStorageList = crudDatabase.load();
                assertEquals(oldStorageList.size() - 1, newStorageList.size());
            } else {
                TaskList addTaskList = new TaskList(oldStorageList);
                Tasks addTask = new Todos("Testing Addition");
                addTaskList.addsTask(addTask, crudDatabase);
                deletesTask_writtenCorrectly_success();
            }
        } catch (FileNotFoundException err) {
            fail("Database loading failed. Test failed testing deletesTask.");
        }
    }

    @Test
    void fileContentCounter_writtenCorrectly_success () {
        TaskList printTaskList = new TaskList(new StorageStub().preloadTaskList());
        assertEquals (4 , printTaskList.fileContentCounter());
    }

    @Test
    void printFileContent_writtenCorrectly_success () {
        TaskList printTaskList = new TaskList(new StorageStub().preloadTaskList());
        assertEquals(true, printTaskList.printFileContent());
    }
}
