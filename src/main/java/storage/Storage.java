package storage;

import tasks.Task;

import java.io.BufferedReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private static final String TASKS_FILENAME = "tasks";

    private final Path path;

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

    public Storage(String appPath) throws StorageOperationException {
        this.path = Storage.createAppDirIfNotExists(appPath);
    }

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
