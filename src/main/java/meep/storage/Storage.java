package meep.storage;

import java.io.BufferedReader;
import java.io.File;
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
import meep.ui.Messages;


/**
 * Represents the file used to store tasks data.
 */
public class Storage {

    public static final String DEFAULT_FILEPATH = "/data.txt";

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
        String path = home + DEFAULT_FILEPATH;
        boolean directoryExists = new java.io.File(path).exists();
        return path;
    }

    /**
     * Checks data file existence and create it if its not exist.
     */
    public void checkFileExists() throws InvalidInputException {
        try {
            File dataFile = new File(path);
            dataFile.createNewFile(); // if file already exists will do nothing
        } catch (IOException e) {
            throw new InvalidInputException(Messages.MESSAGE_FILE_MISSING);
        }
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

        checkFileExists();
        List<Task> result = new ArrayList<>();
        FileInputStream fstream = null;

        try {
            fstream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found under " + path);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        // Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            String[] parts = strLine.split("\\|");
            //  remove leading and trailing spaces
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }

            Parser parser = new Parser();

            boolean isTodo = parts[0].equals("T");
            boolean isDeadline = parts[0].equals("D");
            boolean isEvent = parts[0].equals("E");
            boolean isMarked = parts[1].equals("1");

            if (isTodo) {
                result.add(new ToDo(parts[2], isMarked));
            } else if (isDeadline) {
                result.add(new Deadline(parts[2], isMarked, parser.parseDate(parts[3])));
            } else if (isEvent) {
                result.add(new Event(parts[2], isMarked, parser.parseDate(parts[3])));
            } else {
                throw new AssertionError("Invalid Task!");
            }
        }

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
    public boolean saveTaskToFile(List<Task> taskList) throws IOException {
        String path = getPath();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        FileWriter fw = new FileWriter(path);

        for (Task task : taskList) {
            boolean isTodo = task.getClass() == ToDo.class;
            boolean isDeadline = task.getClass() == Deadline.class;
            boolean isEvent = task.getClass() == Event.class;
            if (isTodo) {
                fw.write("T | " + (task.isDone() ? "1 | " : "0 | ")
                        + task.getTitle() + System.lineSeparator());
            } else if (isDeadline) {
                fw.write("D | " + (task.isDone() ? "1 | " : "0 | ")
                        + task.getTitle() + " | "
                        + ((Deadline) task).getDate().format(format) + System.lineSeparator());
            } else if (isEvent) {
                fw.write("E | " + (task.isDone() ? "1 | " : "0 | ")
                        + task.getTitle() + " | "
                        + ((Event) task).getDate().format(format) + System.lineSeparator());
            } else {
                throw new AssertionError("Invalid Task!");
            }
        }

        fw.close();
        return true;
    }
}
