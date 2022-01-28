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
import java.util.Scanner;

/**
 * Storage is the entity that deals with reading and writing the list of tasks to the user's text file
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for Storage class.
     * @param filePath the location of a textfile containing the user's list of tasks, relative to the root directory
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads in a list of tasks from the user's text file and saves them into an arraylist of Tasks.
     * @return the arraylist of Tasks
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        String dir = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(dir, filePath);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        try {
            File myObj = new File(path.toString());
            Scanner myReader = new Scanner(myObj);
            System.out.println("I see you have an existing list.");
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] data = line.split("\\s*\\|\\s*");
                String task = data[0];
                switch (task) {
                case "T":
                    ToDo td = new ToDo(data[2]);
                    if (data[1].equals("1")) {
                        td.mark();
                    }
                    tasks.add(td);
                    break;
                case "D":
                    Deadline deadline = new Deadline(data[2], LocalDate.parse(data[3], formatter));
                    tasks.add(deadline);
                    if (data[1].equals('1')) {
                        deadline.mark();
                    }
                    break;
                case "E":
                    Event event = new Event(data[2], LocalDate.parse(data[3], formatter));
                    tasks.add(event);
                    if (data[1].equals('1')) {
                        event.mark();
                    }
                    break;
                }
            } myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("It seems you do not have an existing list, I will create it now.");
            try {
                File file = new File(path.toString());
                file.getParentFile().mkdir();
                file.createNewFile();
                System.out.println("file created successfully");
            } catch (IOException ioe) {
                System.out.println("Failed to create file");
            }
        }
        return tasks;
    }

    /**
     * Writes the given arraylist of Tasks out to the user's file.
     * @param tasks the arraylist of Tasks to be saved
     */
    public void saveToFile(ArrayList<Task> tasks) {
        String text = "";
        for (int i = 1; i <= tasks.size(); i++) {
            text += tasks.get(i - 1) + "\n";
        }
        try (PrintWriter out = new PrintWriter(this.filePath)) {
            out.println(text);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to save list");
        }
    }
}
