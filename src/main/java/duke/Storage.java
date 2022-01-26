package duke;

import duke.exception.DukeIoException;
import duke.task.TaskList;
import duke.task.serializer.TaskListSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Handles persistence operations on the file system.
 * This includes creation of relevant directories and files.
 */

public class Storage {
    private static final String DATA_FOLDER_PATH = "data/";
    private static final String DB_FILENAME = "store.db";

    public static TaskList load() throws DukeIoException {
        final FileInputStream dbStream = openDatabaseRead();

        return TaskListSerializer.inflate(dbStream);
    }

    public static void save(TaskList taskList) throws DukeIoException {
        final FileOutputStream dbStream = openDatabaseWrite();

        TaskListSerializer.deflate(taskList, dbStream);
    }

    private static void initDataStore() throws DukeIoException {
        final File dataFolder = new File(DATA_FOLDER_PATH);

        if (dataFolder.exists() && !dataFolder.isDirectory()) {
            throw new DukeIoException("Data Store is not a directory at: "
                    + dataFolder.getAbsolutePath());
        } else if (!dataFolder.exists()) {
            if (!dataFolder.mkdir()) {
                throw new DukeIoException("Cannot create data store directory at: "
                        + dataFolder.getAbsolutePath());
            }
        }
    }

    private static FileInputStream openDatabaseRead() throws DukeIoException {
        initDataStore(); // throws DukeIOException

        final File database = Paths.get(DATA_FOLDER_PATH, DB_FILENAME).toFile();
        try {
            return new FileInputStream(database);
        } catch (FileNotFoundException ex) {
            // Does not create the database until a change is made
            return null;
        }
    }

    private static FileOutputStream openDatabaseWrite() throws DukeIoException {
        initDataStore(); // throws DukeIOException

        final File database = Paths.get(DATA_FOLDER_PATH, DB_FILENAME).toFile();
        try {
            if (!database.exists()) {
                if (!database.createNewFile()) {
                    throw new DukeIoException("Could not create database");
                }
            }

            return new FileOutputStream(database);
        } catch (IOException ex) {
            throw new DukeIoException("Could not create database");
        }
    }
}
