package duke;

import java.io.File;
import java.io.IOException;

import tasks.TaskList;

public interface StorageInterface {
    void writeToFile(String filePath, String textToAdd) throws IOException;
    File load();
    void save(TaskList taskList) throws IOException;
}
