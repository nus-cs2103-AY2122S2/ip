package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mytasks.Deadline;
import mytasks.Event;
import mytasks.Task;

/**
 * The Storage class methods to save and retrieve information from the task list.
 */
public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Creates a new txt file to track the list of task if the file has not been created.
     */
    private void createFile() {
        try {
            File myObj = new File(this.filepath);
            if (myObj.createNewFile()) {
                System.out.println("Files created\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the list of tasks that are currently being tracked into a txt file.
     * @param list contains the Tasks that are currently being tracked.
     */
    public void saveTasks(List<Task> list) {
        try {
            FileWriter myWriter = new FileWriter(this.filepath);
            for (Task task : list) {
                String completed = task.getIsDone() ? "1|" : "0|";
                if (task instanceof Deadline) {
                    myWriter.write("D|" + completed + task.getDescription() + "|"
                            + ((Deadline) task).returnDateTime() + "\n");
                } else if (task instanceof Event) {
                    myWriter.write("E|" + completed + task.getDescription() + "|"
                            + ((Event) task).returnDateTime() + "\n");
                } else {
                    myWriter.write("T|" + completed + task.getDescription() + "\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the list of tasks that were being tracked previously by the application.
     * @return returns the list of task that were being tracked previously.
     */
    public List<String> loadTasks() throws DukeException {
        List<String> list = new ArrayList<>();
        try {
            File myObj = new File(this.filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                list.add(myReader.nextLine());
            }
            myReader.close();
            System.out.println("Files Loaded Successfully\n");
        } catch (FileNotFoundException e) {
            createFile();
            throw new DukeException("No file found. Creating new file...");
        }
        return list;
    }
}
