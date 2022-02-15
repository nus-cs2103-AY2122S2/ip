package duke;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Storage is the entity that deals with reading and writing the list of tasks to the user's text file
 */
public class Storage {

    private final String TASK_FILE_PATH = "data/duke.txt";
    private final String ARCHIVE_FILE_PATH = "data/archive.txt";
    /**
     * Constructor for Storage class.
     */
    public Storage() { }

    /**
     * Reads in a list of tasks from the user's text file and saves them into an arraylist of Tasks.
     * @return the arraylist of Tasks
     */
    public List<ArrayList<Task>> loadTasks() {
        ArrayList<Task> currentTasks = new ArrayList<>();
        readFile(this.TASK_FILE_PATH, currentTasks);
        ArrayList<Task> archivedTasks = new ArrayList<>();
        readFile(this.ARCHIVE_FILE_PATH, archivedTasks);
        return Arrays.asList(currentTasks, archivedTasks);
    }

    public void readFile(String path, ArrayList<Task> tasks) {
        try {
            File myObj = new File(path);
            Scanner sc = new Scanner(myObj);
            while (sc.hasNextLine()) {
                readLine(tasks, sc);
            } sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("It seems you do not have an existing list, I will create it now.");
            try {
                File file = new File(path);
                file.getParentFile().mkdir();
                file.createNewFile();
                System.out.println("file created successfully");
            } catch (IOException ioe) {
                System.out.println("Failed to create file");
            }
        }
    }

    /**
     * Reads a single line from the input file and takes in the given task
     * @param tasks the arraylist of tasks to be saved
     * @param sc scanner to read input
     */
    public void readLine(ArrayList<Task> tasks, Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String line = sc.nextLine();
        String[] data = line.split("\\s*\\|\\s*");
        String task = data[0];
        switch (task) {
        case "T":
            ToDo td = new ToDo(data[2]);
            if (data[1].equals("1")) {
                td.setComplete();
            }
            tasks.add(td);
            break;
        case "D":
            Deadline deadline = new Deadline(data[2], LocalDate.parse(data[3], formatter));
            tasks.add(deadline);
            if (data[1].equals("1")) {
                deadline.setComplete();
            }
            break;
        case "E":
            Event event = new Event(data[2], LocalDate.parse(data[3], formatter));
            tasks.add(event);
            if (data[1].equals("1")) {
                event.setComplete();
            }
            break;
        }
    }

    /**
     * Writes the given arraylist of Tasks out to the user's file.
     * @param tasks the arraylist of Tasks to be saved
     */
    public void saveToFile(ArrayList<Task> tasks, boolean isArchive) {
        String text = "";
        for (int i = 1; i <= tasks.size(); i++) {
            text += tasks.get(i - 1) + "\n";
        }
        String path;
        if (!isArchive) {
            path = this.TASK_FILE_PATH;
        } else {
            path = this.ARCHIVE_FILE_PATH;
        }
        try (PrintWriter out = new PrintWriter(path)) {
            out.println(text);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to save list");
        }
    }
}
