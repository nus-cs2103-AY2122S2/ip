package util;

import DukeBot.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    public static ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> arr = new ArrayList<>();
        File f = new File("data/duke.txt");

        // Task | Completeness (1 = complete, 0 = not yet) | description | date by
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String taskDetails = s.nextLine();
            String[] taskSplit = taskDetails.split("\\|");
            DukeBot.Task task = new DukeBot.Task("empty");

            if (taskSplit[0].equals("T")) {
                task = new DukeBot.ToDo(taskSplit[2]);

            } else if (taskSplit[0].equals("D")) {
                task = new DukeBot.Deadline(taskSplit[2], LocalDateTime.parse(taskSplit[3]));

            } else if (taskSplit[0].equals("E")) {
                task = new DukeBot.Event(taskSplit[2], LocalDateTime.parse(taskSplit[3]));
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

    public static void save(ArrayList<Task> arr) throws IOException {
        FileWriter filewriter = new FileWriter("data/duke.txt");

        // format to save: Task | Completeness (1 = complete, 0 = not yet) | description | date by
        for (int i = 0; i < arr.size(); i++) {
            Task task = arr.get(i);
            filewriter.write(task.dBText());
            filewriter.write("\n");
        }
        filewriter.close();
    }
}
