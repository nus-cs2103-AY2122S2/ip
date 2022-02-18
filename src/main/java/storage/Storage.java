package storage;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import tasks.Task;

/**
 * Represents the retrieval/persistence of tasks to the filesystem.
 */
public class Storage {
    private static final String TASKS_FILENAME = "tasks";

    private final Path path;

    /**
     * Returns a Storage object that can retrieve and overwrite tasks
     * from/to the filesystem.
     *
     * @param appPath represents the base directory that the tasks data file is in.
     * @throws StorageOperationException If the base directory doesn't exist and an
     * exception occurred while automatically creating it.
     */
    public Storage(String appPath) throws StorageOperationException {
        this.path = Storage.createAppDirIfNotExists(appPath);
    }

    private static Path createAppDirIfNotExists(String appPath) throws StorageOperationException {
        final Path appDir = Paths.get("./" + appPath);
        final Path tasksDir = appDir.resolve(Storage.TASKS_FILENAME);

        try {
            Files.createDirectories(appDir);
            tasksDir.toFile().createNewFile();
        } catch (Exception ex) {
            throw new StorageOperationException(ex.getMessage());
        }

        return tasksDir;
    }

    /**
     * Reads and returns the tasks from the filesystem.
     *
     * @return The tasks read from the filesystem.
     * @throws StorageOperationException If an error occurs when reading the tasks from
     * the filesystem.
     */
    public List<Task> load() throws StorageOperationException {
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(this.path);
            final String file = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            return TaskFormatter.decode(file);
        } catch (Exception ex) {
            throw new StorageOperationException(ex.getMessage());
        } finally {
            this.closeReader(reader);
        }
    }

    /**
     * Overwrites the existing tasks in the filesystem with the given set
     * of tasks.
     *
     * @param tasks the set of tasks to be stored in the filesystem.
     * @throws StorageOperationException If an error occurs when writing the tasks to
     * the filesystem.
     */
    public void save(List<? extends Task> tasks) throws StorageOperationException {
        try {
            Files.writeString(this.path, TaskFormatter.encode(tasks));
        } catch (Exception ex) {
            throw new StorageOperationException(ex.getMessage());
        }
    }

    private void closeReader(BufferedReader reader) throws StorageOperationException {
        if (reader != null) {
            try {
                reader.close();
            } catch (Exception ex) {
                throw new StorageOperationException(ex.getMessage());
            }
        }
    }

}
