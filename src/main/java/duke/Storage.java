package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Task;

public class Storage {
    private String filePath = "";
    private String dirPath = "";
    private File file;

    public Storage() { }
    public Storage(String path) {
        String fileSeparator = "/";
        filePath = System.getProperty("user.home") + fileSeparator + path;
        String dir = path.substring(0, path.lastIndexOf("/"));
        dirPath = System.getProperty("user.home") + fileSeparator + dir;
    }

    public TaskManager loadTaskManagerFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        file = new File(filePath);
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String fileInput = scanner.nextLine();
                    try {
                        Task t = Parser.parseToTaskFromFile(fileInput);
                        tasks.add(t);
                    } catch (DukeException exception) {
                        continue;
                    }
                }
                return new TaskManager(tasks);
            } else {
                throw new DukeException("Unable to read Task List from file!");
            }
        } catch (SecurityException | IOException exception) {
            throw new DukeException("Unable to read Task List from file!");
        }
    }
    public boolean saveTaskManager(TaskManager taskManager) throws DukeException {

        try {

            file = new File(filePath);

            if (!dirExists()) {
                boolean dirCreated = createDir();
                if (!dirCreated) {
                    throw new IOException("Unable to create directory!");
                }
            }

            FileWriter writer = new FileWriter(file);

            for (Task task: taskManager.getTaskList()) {
                writer.write(Parser.parseToFileFromTask(task) + "\n");
            }

            writer.close();
            return true;
        } catch (IOException exception) {
            throw new DukeException("Unable to save to disk!");
        }
    }

    public boolean dirExists() {
        File dir = new File(dirPath);
        return dir.exists();
    }
    public boolean createDir() {
        File dir = new File(dirPath);
        return dir.mkdir();
    }

    public String getFullPath() {
        return this.filePath;
    }
}
