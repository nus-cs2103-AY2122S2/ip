package duke.util;

import duke.Tasks.Task;
import duke.Tasks.TaskList;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage is a class that deals with the management of the database
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for the Storage Class. Checks if the file at the specific path is valid. Else it creates
     * a new folder and file to store the data.
     * @param filePath
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        File f = new File(filePath);

        if (!f.exists()) {
            File buildDB = new File("./data/duke.txt");
            if (!buildDB.exists()) {
                buildDB.getParentFile().mkdirs();
                buildDB.createNewFile();
            }
            this.filePath = buildDB.getPath();
        } else {
            this.filePath = filePath;
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> arr = new ArrayList<>();
        File f = new File(filePath);

        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String taskDetails = s.nextLine();
            String[] taskSplit = taskDetails.split("\\|");
            Task task = new Task("empty");

            if (taskSplit[0].equals("T")) {
                task = new ToDo(taskSplit[2]);

            } else if (taskSplit[0].equals("D")) {
                task = new Deadline(taskSplit[2], LocalDateTime.parse(taskSplit[3]));

            } else if (taskSplit[0].equals("E")) {
                task = new Event(taskSplit[2], LocalDateTime.parse(taskSplit[3]));
            }

            if (taskSplit[1].equals("1")) {
                task.toggleCompleted();
            }

            if (task.getDescription() == "empty") {
                // throw an exception
            }
            arr.add(task);
        }
        return arr;
    }

    /**
     * Saves the task into the local database with the following format:
     * Task | Completeness (1 = complete, 0 = not yet) | description | date by (if Task is Event/Deadline)
     * @param arr List of Tasks
     * @throws IOException
     */
    public void save(TaskList arr) throws IOException {
        FileWriter filewriter = new FileWriter(filePath);

        // format to save: Task | Completeness (1 = complete, 0 = not yet) | description | date by
        for (int i = 0; i < arr.getSize(); i++) {
            Task task = arr.get(i);
            filewriter.write(task.dBText());
            filewriter.write("\n");
        }
        filewriter.close();
    }
}
