package duke;

import myTasks.Deadline;
import myTasks.Event;
import myTasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class methods to save and retrieve information from the task list.
 */
public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * The CreateFile method creates a new txt file to track the list of task if the file has not been created.
     */
    private void CreateFile() {
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
     * The savTasks method saves the list of tasks that are currently being tracked into a txt file.
     * @param list contains the Tasks that are currently being tracked.
     */
    public void saveTasks(List<Task> list) {
        try {
            FileWriter myWriter = new FileWriter(this.filepath);
            for (Task task : list) {
                String completed = task.isDone ? "1|" : "0|";
                if (task instanceof Deadline) {
                    myWriter.write("D|" + completed + task.description + "|" + ((Deadline) task).dateTime + "\n");
                } else if (task instanceof Event) {
                    myWriter.write("E|" + completed + task.description + "|" + ((Event) task).dateTime + "\n");
                } else {
                    myWriter.write("T|" + completed + task.description + "\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The loadTasks method retrieves the list of tasks that were being tracked previously by the application.
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
            CreateFile();
            throw new DukeException("No file found. Creating new file...");
        }
        return list;
    }
}