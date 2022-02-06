package storage;

import duke.DukeException;
import task.Task;
import task.TaskList;

/**
 * Interface for storage.
 * Used for StorageStub.
 */
public interface IStorage {
    /**
     * Reads file from the path.
     * @return TaskList list of tasks from file.
     * @throws DukeException
     */
    TaskList readFile() throws DukeException;

    /**
     * Writes the tasks to the file on the path.
     * @param taskList write tasks to file.
     * @throws DukeException
     */
    void writeToFile(TaskList taskList) throws DukeException;

    /**
     * Processes lines from the file.
     * @param line line from file.
     * @return Task corresponding to line.
     * @throws DukeException
     */
    Task processLine(String line) throws DukeException;
}
