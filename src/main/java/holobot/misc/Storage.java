package holobot.misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import holobot.exception.InvalidCommand;
import holobot.task.Deadline;
import holobot.task.Event;
import holobot.task.Task;
import holobot.task.Todo;

/**
 * Deals with the loading/storing of data from/to the hard disk folder.
 * It also provides a function to check if the target data file/folder exist.
 *
 * @author Terng Yan Long
 */
public class Storage {
    public static final String DATA_FOLDER_PATH = "./data";
    public static final String DATA_PATH = "./data/data.txt";
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    /**
     * Instantiates a new storage instance.
     */
    public Storage() {
        try {
            initFileFolder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates new data file/folder if it does not exists.
     *
     * @throws IOException Failed to create data file/folder in the correct location.
     */
    public static void initFileFolder() throws IOException {
        // Check if folder exist
        File dataFolder = new File(DATA_FOLDER_PATH);
        // Create new folder if it does not exist
        if (!dataFolder.isDirectory()) {
            boolean isSuccess = dataFolder.mkdirs();
            if (!isSuccess) {
                throw new IOException("     Failed to create folder.\n"
                        + "     Please create a folder named 'data' in src manually"
                        + "     , before running this program!");
            }
        }

        // Check if file exists
        File dataFile = new File(DATA_PATH);
        // Create new data.txt file if it does not exist
        if (!dataFile.isFile()) {
            boolean isSuccess = dataFile.createNewFile();
            if (!isSuccess) {
                throw new IOException("     Failed to create datafile.\n"
                        + "     Please create a data.txt in src/data manually"
                        + "     , before running this program!");
            }
        }
    }

    /**
     * Initialises the task list according to the size specified.
     * Copies content from data file if it exists on the hard-disk.
     *
     * @param size Size of tasklist
     * @return A TaskList that may contain stored data from previous runs.
     */
    public static TaskList initTaskList(int size) {
        File dataFile = new File(DATA_PATH);
        assert size > 0;
        TaskList taskListOfTasks = new TaskList(size);
        if (dataFile.length() != 0) {
            loadData(dataFile, taskListOfTasks);
        }
        return taskListOfTasks;
    }

    /**
     * Loads data from the data.txt file in the TaskList.
     *
     * @param f File that contains the data.
     * @param listOfTasks Target TaskList to store the data.
     */
    public static void loadData(File f, TaskList listOfTasks) {
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String currCommand = sc.nextLine();
                if (currCommand.startsWith("[T]") & currCommand.length() > 7) { // Valid To-do task
                    Task currTask = createTodo(currCommand);
                    listOfTasks.addTasks(currTask);
                } else if (currCommand.startsWith("[D]")
                        & currCommand.contains("(by:")
                        & currCommand.length() >= 16) { // Valid Deadline task
                    Task currTask = createDeadline(currCommand);
                    listOfTasks.addTasks(currTask);
                } else if (currCommand.startsWith("[E]")
                        & currCommand.contains("(at:")
                        & currCommand.length() >= 16) { // Valid Event task
                    Task currTask = createEvent(currCommand);
                    listOfTasks.addTasks(currTask);
                } // else invalid command => do nothing
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new Todo task based on the command stored.
     *
     * @param currCommand Command stored in the hard disk.
     * @return A Todo task
     */
    private static Todo createTodo(String currCommand) {
        boolean isMarked = currCommand.startsWith("[X]", 3);
        String currDesc = currCommand.substring(7);
        Todo currTask = new Todo(currDesc);
        currTask.setStatus(isMarked);
        return currTask;
    }

    /**
     * Creates a new Deadline task based on the command stored.
     *
     * @param currCommand Command stored in the hard disk.
     * @return A Deadline task.
     */
    private static Deadline createDeadline(String currCommand) {
        boolean isMarked = currCommand.startsWith("[X]", 3);
        int indexBy = currCommand.indexOf("(by:");
        String currDesc = currCommand.substring(7, indexBy - 1);
        String currBy = currCommand.substring(indexBy + 5, currCommand.length() - 1);

        String[] dateTimeArray = currBy.split(" ");

        if (dateTimeArray.length > 4 || dateTimeArray.length < 3) {
            throw new InvalidCommand("Incorrect number of arguments supplied :(");
        }

        // Parse user input into LocalDate/LocalTime if it is in the correct format.
        String currDate = dateTimeArray[0] + " " + dateTimeArray[1] + " " + dateTimeArray[2];
        LocalDate newDate = LocalDate.parse(currDate, dateFormatter);
        LocalTime newTime = null;
        if (dateTimeArray.length > 3) { // Optional time input
            newTime = LocalTime.parse(dateTimeArray[3], timeFormatter);
        }
        DateTimeChecker.checkDateTime(newDate, newTime);
        Deadline currTask = new Deadline(currDesc, newDate, newTime);
        currTask.setStatus(isMarked);
        return currTask;
    }

    /**
     * Creates a new Event task based on the command stored.
     *
     * @param currCommand Command stored in the hard disk.
     * @return An Event task.
     */
    private static Event createEvent(String currCommand) {
        boolean isMarked = currCommand.startsWith("[X]", 3);
        int indexAt = currCommand.indexOf("(at:");
        String currDesc = currCommand.substring(7, indexAt - 1);
        String currAt = currCommand.substring(indexAt + 5, currCommand.length() - 1);

        String[] dateTimeArray = currAt.split(" ");

        if (dateTimeArray.length > 4 || dateTimeArray.length < 3) {
            throw new InvalidCommand("Incorrect number of arguments supplied :(");
        }

        // Parse user input into LocalDate/LocalTime if it is in the correct format.
        String currDate = dateTimeArray[0] + " " + dateTimeArray[1] + " " + dateTimeArray[2];
        LocalDate newDate = LocalDate.parse(currDate, dateFormatter);
        LocalTime newStartTime = null;
        LocalTime newEndTime = null;
        if (dateTimeArray.length > 3) { // Optional start time input
            String[] startEndTime = dateTimeArray[3].split("-");
            newStartTime = LocalTime.parse(startEndTime[0], timeFormatter);
            if (startEndTime.length == 2) {
                newEndTime = LocalTime.parse(startEndTime[1], timeFormatter);
            }
        }

        DateTimeChecker.checkDateTime(newDate, newStartTime, newEndTime);

        Event currTask = new Event(currDesc, newDate, newStartTime, newEndTime);
        currTask.setStatus(isMarked);
        return currTask;
    }
}
