package athena.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import athena.tasks.TaskList;

/**
 * Contains methods for saving and loading the current state of the
 * task list onto the disk.
 */
public class Storage {
    private Path directoryPath;
    private Path savePath;

    /**
     * Constructs a new Storage instance with the given save location information.
     *
     * @param saveDirectory Relative location of directory for saving/loading.
     * @param saveFileName Name of the file for saving/loading data. (e.g. Athena.txt)
     */
    public Storage(String saveDirectory, String saveFileName) {
        directoryPath = Paths.get(saveDirectory);
        savePath = directoryPath.resolve(saveFileName);
    }

    /**
     * Saves the given TaskList instance to disk.
     *
     * @param taskList The TaskList instance to save to disk.
     * @throws IOException If unable to save to disk successfully.
     */
    public void saveToDisk(TaskList taskList) throws IOException {
        // Create the data directory if necessary
        if (!Files.exists(directoryPath)) {
            Files.createDirectory(directoryPath);
        }

        // Save the file, overriding any existing save
        List<String> taskListSaveFormat = taskList.getSaveFormat();
        Files.write(savePath, taskListSaveFormat);
    }

    /**
     * Returns true if an existing save file can be located at the save directory.
     *
     * @return True if an existing save file exists at the save directory.
     */
    public boolean hasExistingSave() {
        return Files.exists(savePath);
    }

    /**
     * Returns a TaskList instance that has been loaded from disk.
     *
     * @return TaskList instance loaded from disk.
     * @throws IOException If unable to load from disk successfully.
     */
    public TaskList loadFromDisk() throws IOException {
        List<String> taskListSaveFormat = Files.readAllLines(savePath);
        return new TaskList(taskListSaveFormat);
    }
}
