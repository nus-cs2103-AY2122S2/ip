package athena.storage;

import athena.tasks.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.nio.file.Path;

public class Storage {
    private Path directoryPath;
    private Path savePath;

    public Storage(String saveDirectory, String saveFileName) {
        directoryPath = Paths.get(saveDirectory);
        savePath = directoryPath.resolve(saveFileName);
    }

    public void saveToDisk(TaskList taskList) throws IOException {
        // Create the data directory if necessary
        if (!Files.exists(directoryPath)) {
            Files.createDirectory(directoryPath);
        }

        // Save the file, overriding any existing save
        List<String> taskListSaveFormat = taskList.getSaveFormat();
        Files.write(savePath, taskListSaveFormat);
    }

    public boolean hasExistingSave() {
        return Files.exists(savePath);
    }

    public TaskList loadFromDisk() throws IOException {
        List<String> taskListSaveFormat = Files.readAllLines(savePath);
        return new TaskList(taskListSaveFormat);
    }
}
