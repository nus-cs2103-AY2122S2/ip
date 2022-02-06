package storage;

import duke.DukeException;
import task.Task;
import task.TaskList;

public class StorageStub implements IStorage{

    @Override
    public TaskList readFile() throws DukeException {
        return new TaskList();
    }

    @Override
    public void writeToFile(TaskList taskList) throws DukeException {

    }

    @Override
    public Task processLine(String line) throws DukeException {
        return null;
    }
}
