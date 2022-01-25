package duke.util;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public interface Storage {
    boolean isDatabaseExists() throws IOException;
    void readFileContent(ArrayList<Task> storingList) throws IOException;
    long getDatabaseLength();
    void changeStatusTask(int lineNumber, Task task) throws IOException;
    void addTaskToText(Task task) throws IOException;
    void deleteTask(int lineNumber) throws IOException;
}
