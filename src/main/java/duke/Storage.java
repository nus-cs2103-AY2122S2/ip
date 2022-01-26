package duke;

import duke.exception.DukeIOException;
import duke.task.TaskList;
import duke.task.serializer.TaskListSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Handles all persistence-related operations on the file system for the inflation and serialization
 * of the <code>TaskList</code> object. Performs the creation of relevant directory structures and files.
 */
public class Storage {
    /** Name of the data directory */
    private static final String DATA_FOLDER_PATH = "data/";

    /** Name of the database file */
    private static final String DB_FILENAME = "store.db";

    /**
     * Loads any previous data, if any, that was saved by a previous run of the application.
     * Inflates the <code>TaskList</code> object stored by the previous run.
     * @return <code>TaskList</code> object read from database file.
     * @throws DukeIOException If an error occurs during any I/O operation or the database file read is
     *                         in an invalid format.
     */
    public static TaskList load() throws DukeIOException {
        FileInputStream dbStream = openDatabaseRead();

        return TaskListSerializer.inflate(dbStream);
    }

    /**
     * Saves the provided <code>TaskList</code> object to disk.
     * Serializes and writes the object to a predetermined location on the file system.
     * @param taskList <code>TaskList</code> object to save.
     * @throws DukeIOException If an error occurs during the write operation.
     */
    public static void save(TaskList taskList) throws DukeIOException {
        FileOutputStream dbStream = openDatabaseWrite();

        TaskListSerializer.deflate(taskList, dbStream);
    }

    /**
     * Creates the directory structure required for saving the database file.
     * @throws DukeIOException If an error occurs during folder creation.
     */
    private static void initDataStore() throws DukeIOException {
        File dataFolder = new File(DATA_FOLDER_PATH);

        if (dataFolder.exists() && !dataFolder.isDirectory()) {
            throw new DukeIOException("Data Store is not a directory at: "
                    + dataFolder.getAbsolutePath());
        } else if (!dataFolder.exists()) {
            if (!dataFolder.mkdir()) {
                throw new DukeIOException("Cannot create data store directory at: "
                        + dataFolder.getAbsolutePath());
            }
        }
    }

    /**
     * Creates a read-stream from the database file on disk.
     * @return <Code>FileInputStream</Code> for the database file.
     * @throws DukeIOException If an error occurs during the internal {@link #initDataStore()} operation.
     */
    private static FileInputStream openDatabaseRead() throws DukeIOException {
        initDataStore(); // throws DukeIOException

        File database = Paths.get(DATA_FOLDER_PATH, DB_FILENAME).toFile();
        try {
            return new FileInputStream(database);
        } catch (FileNotFoundException ex) {
            // Does not create the database until a change is made
            return null;
        }
    }

    /**
     * Creates a write-stream to the database file stored on disk.
     * Overwrites any existing data in the file.
     * @return <code>FileOutputStream</code> for the database file.
     * @throws DukeIOException If an error occurs during the internal {@link #initDataStore()} or write
     *                         operation.
     */
    private static FileOutputStream openDatabaseWrite() throws DukeIOException {
        initDataStore(); // throws DukeIOException

        File database = Paths.get(DATA_FOLDER_PATH, DB_FILENAME).toFile();
        try {
            if (!database.exists()) {
                if (!database.createNewFile()) {
                    throw new DukeIOException("Could not create database");
                }
            }

            return new FileOutputStream(database);
        } catch (IOException ex) {
            throw new DukeIOException("Could not create database");
        }
    }
}
