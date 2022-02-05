package angela.task;

import java.io.IOException;
import java.util.ArrayList;

import angela.util.Storage;

public class StorageStub implements Storage {
    private long length;

    /**
     * Initialize a stub for database class
     * @param path Relative path to the database
     * @param directory Relative directory to the database
     */
    public StorageStub(String path, String directory) {
        if (path.equals("data/duke.Duke.txt") && directory.equals("data")) {
            this.length = 1;
        } else {
            this.length = 0;
        }
    }

    public long getDatabaseLength() {
        return this.length;
    }

    @Override
    public void changeStatusTask(int lineNumber, Task task) throws IOException {

    }

    @Override
    public void addTaskToDatabase(Task task) throws IOException {

    }

    @Override
    public void deleteTask(int lineNumber) throws IOException {

    }

    @Override
    public boolean doesDatabaseExists() throws IOException {
        return false;
    }

    @Override
    public void readFileContent(ArrayList<Task> storingList) throws IOException {
        storingList.add(new Task("return book /by 2/12/2019", "D"));
        storingList.add(new Task("return book /by 2/12/2019", "D"));
    }
}
