package mickey.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mickey.task.Deadline;
import mickey.task.Event;
import mickey.task.Task;
import mickey.task.TaskList;
import mickey.task.ToDo;

/**
 * Storage object to perform task load and store from tasks.txt file.
 */
public class Storage {
    /** Name of the file containing saved tasks. */
    private final String fileName;

    /** Path of the file containing saved tasks. */
    private final String filePath;

    /**
     * Constructor.
     *
     * @param fileName path of the file containing saved tasks.
     */
    public Storage(String fileName) {
        String wd = System.getProperty("user.dir");
        this.filePath = Path.of(wd + File.separator + "data").toAbsolutePath().toString();
        this.fileName = fileName;
    }

    /**
     * Load tasks from text file.
     *
     * @return List of tasks loaded from file.
     * @throws MickeyException exception for task loading error.
     */
    public List<Task> load() throws MickeyException {
        List<Task> tasks = new ArrayList<>();
        File f = new File(this.filePath + File.separator + this.fileName);
        if (f.exists()) {
            try {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String[] toAdd = sc.nextLine().split("\\|");
                    Task t;

                    switch (toAdd[0]) {
                    case "T":
                        t = new ToDo(toAdd[2]);
                        break;
                    case "D":
                        t = new Deadline(toAdd[2], toAdd[3]);
                        break;
                    case "E":
                        t = new Event(toAdd[2], toAdd[3]);
                        break;
                    default:
                        throw new MickeyException("Failed to load task\n");
                    }

                    if (toAdd[1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        return tasks;
    }

    /**
     * Save tasks into text file.
     *
     * @param tasks List of tasks.
     */
    public void save(TaskList tasks) {
        try {
            File f = new File(this.filePath);
            f.mkdir();
            FileWriter fw = new FileWriter(this.filePath + File.separator + this.fileName);
            for (Task t : tasks.getTasks()) {
                StringBuilder newTask = new StringBuilder();
                if (t instanceof ToDo) {
                    newTask.append("T|");
                } else if (t instanceof Deadline) {
                    newTask.append("D|");
                } else {
                    assert t instanceof Event;
                    newTask.append("E|");
                }

                if (t.isDone()) {
                    newTask.append("1|");
                } else {
                    newTask.append("0|");
                }

                newTask.append(t.getDescription());

                if (t instanceof Deadline) {
                    newTask.append("|");
                    newTask.append(((Deadline) t).getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
                } else if (t instanceof Event) {
                    newTask.append("|");
                    newTask.append(((Event) t).getAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
                }
                newTask.append("\r\n");
                fw.write(newTask.toString());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
