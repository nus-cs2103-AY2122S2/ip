package duke.task;

import duke.util.Storing;

import java.io.IOException;
import java.util.ArrayList;

public class StoringStub implements Storing {
    private long length;

    public StoringStub(String path, String directory) {
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
    public void addTaskToText(Task task) throws IOException {

    }

    @Override
    public void deleteTask(int lineNumber) throws IOException {

    }

    @Override
    public boolean isDatabaseExists() throws IOException {
        return false;
    }

    @Override
    public void readFileContent(ArrayList<Task> storingList) throws IOException {
        storingList.add(new Task("return book /by 2/12/2019", "D"));
        storingList.add(new Task("return book /by 2/12/2019", "D"));
    }
}
