package angela.util;

import java.io.IOException;
import java.util.ArrayList;

import angela.task.Task;

/**
 * Interface represent object that can store things
 */
public interface Storage {
    /**
     * Checks whether the database file exist or not
     *
     * @return True if the database file exists and false otherwise
     * @throws IOException If an I/O error occur
     */
    boolean doesDatabaseExists() throws IOException;

    /**
     * Reads the database file and append all the tasks into a collection
     *
     * @param storingList The collection need to append
     * @throws IOException If an I/O error occur
     */
    void readFileContent(ArrayList<Task> storingList) throws IOException;

    /**
     * Retrieves the total length of the database
     *
     * @return The length of database file
     */
    long getDatabaseLength();

    /**
     * Changes the status of the task in the database
     *
     * @param lineNumber The task location in the list
     * @param task       The task need to be changed
     * @throws IOException If an I/O error occur
     */
    void changeStatusTask(int lineNumber, Task task) throws IOException;

    /**
     * Adds content of the task into the database
     *
     * @param task The task need to be added
     * @throws IOException If an I/O error occur
     */
    void addTaskToDatabase(Task task) throws IOException;

    /**
     * Deletes a specific task in the database
     *
     * @param lineNumber The task location in the list
     * @throws IOException If an I/O error occur
     */
    void deleteTask(int lineNumber) throws IOException;
}
