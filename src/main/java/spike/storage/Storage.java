package spike.storage;

import static spike.task.Task.DATE_TIME_PATTERN;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import spike.exception.SpikeException;
import spike.task.Deadline;
import spike.task.Event;
import spike.task.Task;
import spike.task.TaskList;
import spike.task.ToDo;


/**
 * Saves and loads task list to and from the hard disk.
 */
public class Storage {
    public static final String MSG_ERROR_CREATING_FILE = "Sorry, I couldn't create the task list file for you.";
    public static final String MSG_ERROR_SAVING_FILE = "Oops, something went wrong with saving your file :(";
    private String directory;
    private String filePath;

    /**
     * Constructor using relative path of data folder and file.
     *
     * @param directory relative path of folder where the data file sits
     * @param filePath relative path of the data file
     */
    public Storage(String directory, String filePath) {
        this.directory = directory;
        this.filePath = filePath;
    }

    /**
     * Gets the task list from hard disk.
     * If the file and folder does not exist, create them.
     *
     * @return a list of tasks retrieved from data file
     * @throws SpikeException if the path given is invalid
     */
    public ArrayList<Task> load() throws SpikeException {
        validateFolder();
        File dataFile = validateFile();
        if (dataFile.length() == 0) {
            return new ArrayList<>();
        }
        return loadFile(dataFile);
    }

    /**
     * Validates the existence of data file.
     *
     * @return a valid file object
     * @throws SpikeException if there's error in creating file
     */
    private File validateFile() throws SpikeException {
        // Check whether the data file exists, if not, create it
        File dataFile = new File(System.getProperty("user.dir") + filePath);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new SpikeException(MSG_ERROR_CREATING_FILE);
            }
        }
        return dataFile;
    }

    /**
     * Loads tasks from hard disk.
     *
     * @param dataFile file path
     * @return loaded task list
     */
    private ArrayList<Task> loadFile(File dataFile) {
        // Load data from the file if it is not empty
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner fileRead = new Scanner(dataFile);
            while (fileRead.hasNextLine()) {
                String currLine = fileRead.nextLine();
                String[] info = currLine.split(" \\| ");
                Task task = null;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
                switch (info[0]) {
                case "T":
                    task = new ToDo(info[2]);
                    break;
                case "E":
                    task = new Event(info[2], LocalDateTime.parse(info[3], dtf));
                    break;
                case "D":
                    task = new Deadline(info[2], LocalDateTime.parse(info[3], dtf));
                    break;
                default:
                    break;
                }
                tasks.add(task);
                if (info[1].equals("1")) {
                    task.markAsDone();
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Validates existence of data folder
     */
    private void validateFolder() {
        // Check first whether the data folder exists, if not, create it
        File dataDir = new File(System.getProperty("user.dir") + directory);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }

    /**
     * Saves the latest task list into hard disk.
     *
     * @param tasks current task list
     */
    public void saveChanges(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(System.getProperty("user.dir") + filePath);
            String latestList = "";
            for (Task task : tasks.getTasks()) {
                latestList = latestList + task.toFileFormat() + "\n";
            }
            fw.write(latestList);
            fw.close();
        } catch (IOException e) {
            System.out.println(MSG_ERROR_SAVING_FILE);
        }
    }
}
