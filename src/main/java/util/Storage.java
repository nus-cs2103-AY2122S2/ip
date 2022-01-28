package util;

import Tasks.Task;
import Tasks.TaskList;

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

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> arr = new ArrayList<>();
        File f = new File(filePath);

        // Task | Completeness (1 = complete, 0 = not yet) | description | date by
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String taskDetails = s.nextLine();
            String[] taskSplit = taskDetails.split("\\|");
            Tasks.Task task = new Tasks.Task("empty");

            if (taskSplit[0].equals("T")) {
                task = new Tasks.ToDo(taskSplit[2]);

            } else if (taskSplit[0].equals("D")) {
                task = new Tasks.Deadline(taskSplit[2], LocalDateTime.parse(taskSplit[3]));

            } else if (taskSplit[0].equals("E")) {
                task = new Tasks.Event(taskSplit[2], LocalDateTime.parse(taskSplit[3]));
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
