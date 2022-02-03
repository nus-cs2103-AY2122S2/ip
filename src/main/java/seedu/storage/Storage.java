package seedu.storage;

import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Task;
import seedu.duke.DukeException;
import seedu.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final File file;

    public Storage(String filePath) {
        file = Paths.get(filePath).toFile();
    }

    public ArrayList<Task> load() throws DukeException {

        if (Paths.get(file.getParent()).toFile().mkdirs()) {
            System.out.println("Info: Parent directory created.");
        } else {
            System.out.println("Info: Parent directory found.");
        }

        if (createFile()) {
            throw new DukeException(file.getAbsolutePath() + " created.");
        } else {
            System.out.println("Info: Save file found.");
            try {
                Scanner sc = new Scanner(file);
                ArrayList<Task> tasks = new ArrayList<>();

                while (sc.hasNextLine()) {
                    String[] task = sc.nextLine().split("\t");
                    boolean isCompleted = Boolean.parseBoolean(task[2]);

                    switch (task[0]) {
                    case "T":
                        tasks.add(new Todo(task[1], isCompleted));
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(task[3], Task.getFormatter());
                        tasks.add(new Deadline(task[1], isCompleted, by));
                        break;
                    case "E":
                        LocalDateTime at = LocalDateTime.parse(task[3], Task.getFormatter());
                        tasks.add(new Event(task[1], isCompleted, at));
                        break;
                    default:
                        throw new DukeException("Unknown task type found: " + task[0]);
                    }
            }

            return tasks;
            } catch (FileNotFoundException e) {
                throw new DukeException("File for some reason cannot be found lol.");
            }

        }
    }

    private boolean createFile() throws DukeException {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("File creation error. So, cannot save.");
        }
    }

    public void saveAll(ArrayList<Task> tasks) throws DukeException{
        try {
            FileWriter fw = new FileWriter(file);

            for (Task t : tasks) {
                fw.write(t.toFile() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Save error occurred. Cannot save.");
        }
    }
}
