package duke.storage;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * CLass responsible for interacting with List of task storing and loading from storage.
 */
public class Storage {
    private final String filepath;
    private final String filename;

    /**
     * Constructor for the Storage class.
     */
    public Storage() {
        this.filepath = System.getProperty("user.dir") + File.separator + "data";
        this.filename = "duke.Storage.txt";
        try {
            if (!Files.exists(Paths.get(filepath))) {
                Files.createDirectories(Paths.get(filepath));
            }
            if (!Files.exists(Paths.get(filepath + File.separator + filename))) {
                File file = new File(filepath + File.separator + filename);
                boolean result = file.createNewFile();
                if (!result) {
                    System.out.println("Unable to create file");
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to create path/file");
        }
    }

    /**
     * Reads from the storage and returns the stored list of tasks.
     *
     * @return an array list containing the stored list of tasks
     */
    public ArrayList<Task> readFromStorage() {
        ArrayList<Task> listOfTasks = new ArrayList<>();

        String st;
        Task task = new Task("null");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filepath
                                                        + File.separator + this.filename));
            st = reader.readLine();
            while (st != null) {
                task = parseStorage(st);
                listOfTasks.add(task);
                st = reader.readLine();
            }
            reader.close();
        } catch (DukeException ex) {
            System.out.println("IOException while trying to read from file");
        } catch (IOException ex) {
            System.out.println("Error in storage format");
            ex.printStackTrace();
        }
        return listOfTasks;
    }

    private Task parseStorage(String storedTask) throws DukeException {
        Task task;
        String[] compactTask = storedTask.split("-");
        switch (compactTask[0]) {
        case "T":
            task = new ToDo(compactTask[2]);
            break;
        case "D":
            task = new Deadline(compactTask[2], compactTask[3]);
            break;
        case "E":
            task = new Event(compactTask[2], compactTask[3]);
            break;
        default:
            throw new DukeException("Incorrect format in Memory!");
        }
        if (compactTask[1].equals("1")) {
            task.mark();
        }
        return task;
    }

    /**
     * Writes all currently stored tasks into storage.
     *
     * @param listOfTasks TaskList containing the list of tasks to be written to storage
     * @return String containing the task in the storage format
     */
    public String writeToStorage(TaskList listOfTasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filepath
                                                        + File.separator + this.filename));
            for (Task task : listOfTasks.getList()) {
                try {
                    writer.write(formatTask(task));
                } catch (IOException e) {
                    return "Unable to write";
                }
            }
            writer.close();
        } catch (IOException e) {
            return "Unable to create writer";
        }
        return "null";
    }

    private String formatTask(Task task) {
        ArrayList<String> toFormat = task.makeCompact();
        String formattedString;

        formattedString = String.join("-", toFormat);
        formattedString = formattedString + "\n";
        return formattedString;
    }
}
