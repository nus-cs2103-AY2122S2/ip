package meep.storage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import meep.exception.InvalidInputException;
import meep.parser.Parser;
import meep.task.Deadline;
import meep.task.Event;
import meep.task.ListTask;
import meep.task.Task;
import meep.task.ToDo;


/**
 * Represents the file used to store tasks data.
 */
public class Storage {

    public static final String DEFAULT_FILEPATH = "/src/main/java/Meep/data.txt";

    public final String path;

    /**
     * Constructor for class Storage.
     */
    public Storage() {
        this.path = getPath();
    }

    /**
     * Constructor for class Storage.
     */
    public Storage(String path, ListTask tasks) throws IOException, InvalidInputException {
        this.path = getPath();
        tasks.addTaskList(this.readTaskFile(this.getPath()));
    }

    /**
     * Gets path.
     *
     * @return the path.
     */
    public String getPath() {
        String home = System.getProperty("user.dir");
        // works on *nix
        // works on Windows
        String path = home + DEFAULT_FILEPATH;
        boolean directoryExists = new java.io.File(path).exists();
        return path;
    }

    /**
     * Read task date file.
     *
     * @param path the path of the file.
     * @return the list of tasks.
     * @throws IOException           If the file path is invalid.
     * @throws InvalidInputException If the datetime format is invalid.
     */
    public List<Task> readTaskFile(String path) throws IOException, InvalidInputException {

        List<Task> result = new ArrayList<>();
        // String path = getPath();
        // Open the file
        FileInputStream fstream = null;

        try {
            fstream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            //System.out.println("File not found under " + path);
            throw new FileNotFoundException("File not found under " + path);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        // Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console
            System.out.println(strLine);
            String[] parts = strLine.split("\\|");
            //  remove leading and trailing spaces
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }

            Parser parser = new Parser();
            if (parts[0].equals("T")) {
                result.add(new ToDo(parts[2], parts[1].equals("1")));
            } else if (parts[0].equals("D")) {
                result.add(new Deadline(parts[2],
                        parts[1].equals("1"), parser.parseDate(parts[3])));
            } else if (parts[0].equals("E")) {
                result.add(new Event(parts[2],
                        parts[1].equals("1"), parser.parseDate(parts[3])));
            } else {
                System.out.println(parts[0]);
            }
        }

        // Close the input stream
        fstream.close();
        return result;
    }


    /**
     * Saves task list to file.
     *
     * @param taskList the task list.
     * @return status of saving.
     * @throws FileNotFoundException If the file not found.
     */
    public boolean saveTaskToFile(List<Task> taskList) throws FileNotFoundException {
        String path = getPath();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            FileWriter fw = new FileWriter(path);
            for (Task task : taskList) {
                if (task.getClass() == ToDo.class) {
                    fw.write("T | " + (task.getDone() ? "1 | " : "0 | ")
                            + task.getTitle() + System.lineSeparator());
                } else if (task.getClass() == Deadline.class) {
                    fw.write("D | " + (task.getDone() ? "1 | " : "0 | ")
                            + task.getTitle() + " | "
                            + ((Deadline) task).getDate().format(format) + System.lineSeparator());
                } else if (task.getClass() == Event.class) {
                    fw.write("E | " + (task.getDone() ? "1 | " : "0 | ")
                            + task.getTitle() + " | "
                            + ((Event) task).getDate().format(format) + System.lineSeparator());
                }
            }

            fw.close();
        } catch (IOException e) {
            throw new FileNotFoundException("File not found under " + path);
        }
        return true;
    }
}
