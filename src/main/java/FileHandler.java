import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * Manges the I/O operations (saving and loading) locally stored
 * task data.
 */
public class FileHandler {

    /**
     * File used to save task data locally.
     */
    private static File FILE = new File(".\\data\\duke.txt");

    /**
     * List of user tasks.
     */
    private ArrayList<Task> taskList;


    /**
     * Constructor to create File Handler.
     *
     * @param list task list of user.
     */
    public FileHandler(ArrayList<Task> list) {
        this.taskList = list;
    }

    /**
     * Create the file and necessary folder to store tasks.
     *
     * @throws FileIOException exception when unable to create file or folder.
     */
    public void createFile() throws FileIOException {
        try {
            FILE.getParentFile().mkdirs();
            FILE.createNewFile();
        } catch (IOException exception) {
            throw new FileIOException(exception.getMessage());
        }
    }

    /**
     * Saves the tasks from task list to the file stored locally.
     *
     * @throws FileIOException exception when unable to create or write to file.
     */
    protected void saveToFile() throws FileIOException {
        try {
            if (!FILE.exists()) {
                createFile();
            }
            FileWriter writer = new FileWriter(FILE);
            TaskType type;
            String doneIndicator;
            for (Task task: taskList) {
                type = task.getType();
                doneIndicator = (task.getIsDone() ? "1" : "0");
                switch (type) {
                case TODO:
                    writer.write("T | " + doneIndicator + " | " + task.getDescription());
                    break;
                case DEADLINE:
                    Deadline deadline = (Deadline) task;
                    writer.write("D | " + doneIndicator + " | " + task.getDescription()
                            + " | " + deadline.byToString());
                    break;
                case EVENT:
                    Event event = (Event) task;
                    writer.write("T | " + doneIndicator + " | " + task.getDescription()
                            + " | " + event.startToString() + " | " + event.endToString());

                    break;
                }
            }
            writer.close();
        } catch (IOException exception) {
            throw new FileIOException(exception.getMessage());
        }
    }

    /**
     * Read the data/tasks from the file and load to the Duke program.
     *
     * @throws DukeException exceptions when reading Invalid Syntax of file or creating file.
     */
    protected void readFromFile() throws DukeException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE));
            String line;
            String[] tokens;
            Task task;

            while ((line = bufferedReader.readLine()) != null) {
                tokens = line.split("\\|");
                tokens[0] = tokens[0].trim();
                if (tokens[0].equals("T")) {
                    task = new ToDo(tokens[2].trim());
                } else if (tokens[0].equals("D")) {
                    task = new Deadline(tokens[2].trim(), LocalDateTime.parse(tokens[3].trim()));
                } else if (tokens[0].equals("E")) {
                    task = new Event(tokens[2].trim(), LocalDateTime.parse(tokens[3].trim()),
                            LocalDateTime.parse(tokens[4].trim()));
                } else {
                    throw new InvalidFileSyntaxException("Failed to load task: Unknown Task type");
                }

                tokens[1] = tokens[1].trim();
                if (tokens[1].equals("1")) {
                    task.markAsDone();
                } else if (tokens[1].equals("0")) {
                    task.unmarkAsDone();
                } else {
                    throw new InvalidFileSyntaxException("Failed to load task: Unkown Task Mark Syntax");
                }
                taskList.add(task);
            }
            bufferedReader.close();
        } catch (FileNotFoundException exception) {
            this.createFile();
        } catch (IOException exception) {
           throw new FileIOException(exception.getMessage());
        }
    }
}
