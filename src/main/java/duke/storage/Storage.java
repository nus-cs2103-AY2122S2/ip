package duke.storage;

import duke.dukeexceptions.DukeExceptions;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage object acts as an interface between Duke and the filename for database management.
 */
public class Storage{
    /** The file which stores the tasks in the task list. */
    File file;

    /**
     * Creates a new Storage object with the filename to store and retrieves the data in the tasks list.
     *
     * @param filename THe file where the tasks in the tasks list is retrieved and stored.
     */
    public Storage(String filename) {
        this.file = new File(filename);
    }

    /**
     * Gets all the tasks in file and creates a new task list based on the data retrieved.
     *
     * @return Task List with all the tasks retrieved from the file filename.
     * @throws FileNotFoundException If the file filename could not be found in duke.
     */
    public TaskList getData() throws FileNotFoundException {
        Scanner fileInput = new Scanner(this.file);
        TaskList taskList = new TaskList();
        while (fileInput.hasNextLine()) {
            String type = fileInput.nextLine();
            Boolean done = Boolean.parseBoolean(fileInput.nextLine());
            String name = fileInput.nextLine();
            String date = fileInput.nextLine();
            if (date.equals("*** Next Task ***")) {
                date = null;
            } else {
                fileInput.nextLine();
            }
            try {
                taskList.addTask(Task.createTask(type, done, name, date));
            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
            }
        }
        return taskList;
    }

    /**
     * Updates the file filename with the latest data from the task list.
     *
     * @param taskList The task list which has the latest data.
     * @throws IOException If an IO error happens.
     */
    public void updateData(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(taskList.updateDatabase());
        fw.close();
    }
}
