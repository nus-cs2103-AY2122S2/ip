package duke.storage;

import duke.dukeexceptions.DukeExceptions;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage{
    private File file;

    public Storage(String filename) {
        this.file = new File(filename);
    }

    public TaskList getData() throws FileNotFoundException {
        Scanner fileInput = new Scanner(file);
        TaskList taskList = new TaskList();

        // Gets the data from the file and creates new task into the task list.
        while (fileInput.hasNextLine()) {
            // Gets the properties for a task.
            String type = fileInput.nextLine();
            Boolean done = Boolean.parseBoolean(fileInput.nextLine());
            String name = fileInput.nextLine();
            String date = fileInput.nextLine();

            // If the program finishes processing a task.
            if (date.equals("*** Next Task ***")) {
                date = null;
            } else {
                fileInput.nextLine();
            }

            // Adds the task to the task list.
            try {
                taskList.addTask(Task.createTask(type, done, name, date));
            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
            }
        }
        return taskList;
    }

    public void updateData(TaskList taskList) throws IOException {
        // Creates a new filewriter.
        FileWriter fw = new FileWriter(file);

        // Writes all the tasks in the task list into the file.
        fw.write(taskList.updateDatabase());

        // Closes the filewriter.
        fw.close();
    }
}
