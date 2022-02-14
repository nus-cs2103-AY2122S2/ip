package yeowoo;

import java.io.File;
import java.io.IOException;

import tasks.TaskList;

public class StorageStub implements StorageInterface {

    @Override
    public void writeToFile(String filePath, String textToAdd) throws IOException {

    }

    @Override
    public File load() {
        return new File("Test/test.txt");
    }

    @Override
    public void save(TaskList taskList) throws IOException {

    }
}
