package duke.operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a storage of Duke. It deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    protected static String filePath;
    protected static String currWorkingDirectory;
    private static final int IS_UNMARKED = 0;
    private static final int IS_MARKED = 1;
    private static final int DESCRIPTION = 2;
    private static final int START_DATE = 3;
    private static final int START_TIME = 4;
    private static final int END_TIME = 5;

    /**
     * A Storage constructor to initialise a <code>Storage</code> object. A <code>Storage</code>
     * is represented by a String, String.
     * E.g., <code>System.getProperty("user.home"), /DukeSaveDirectory/DukeSaveFile.txt</code>.
     *
     * @param filePath the txt file to store the task data.
     * @param currWorkingDirectory the directory where the txt file should be stored.
     */
    public Storage(String filePath, String currWorkingDirectory) {
        Storage.filePath = filePath;
        Storage.currWorkingDirectory = currWorkingDirectory;
    }

    /**
     * Updates the task data into the txt file.
     *
     * @throws DukeException if unable to write to txt file.
     */
    public static void updateTextFile() throws DukeException {
        try {
            writeToFile(currWorkingDirectory + filePath);
        } catch (IOException e) {
            throw new DukeException("Cannot write to txt file!");
        }
    }

    /**
     * Writes the task data into the txt file.
     *
     * @param filePath the txt file to store the task data.
     * @throws IOException the txt file cannot be written into.
     */
    public static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task str : TaskList.TASK_ARRAY_LIST) {
            fw.write(formatTextFileLine(str) + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Formats the task data before writing into the txt file.
     *
     * @param task the task to be formatted.
     * @return the formatted task data.
     */
    public static String formatTextFileLine(Task task) {
        String finalOutput = null;
        int mark = (task.getStatusIcon().equals("X") ? IS_MARKED : IS_UNMARKED);
        if (task instanceof ToDo) {
            finalOutput = "T|" + mark + "|" + task.getDescription();
        } else if (task instanceof Deadline) {
            finalOutput = "D|" + mark + "|" + task.getDescription()
                    + "|" + Parser.convertLocalDateToString(((Deadline) task).getDate())
                    + "|" + Parser.convertLocalTimeToString(((Deadline) task).getTime());
        } else if (task instanceof Event) {
            finalOutput = "E|" + mark + "|" + task.getDescription()
                    + "|" + Parser.convertLocalDateToString(((Event) task).getDate())
                    + "|" + Parser.convertLocalTimeToString(((Event) task).getStartTime())
                    + "|" + Parser.convertLocalTimeToString(((Event) task).getEndTime());
        }
        return finalOutput;
    }

    /**
     * Loads the txt file into Duke. It retrieves the task data from its previous use.
     * Creates a directory or txt file if it does not exist.
     *
     * @throws IOException the txt file cannot be loaded.
     */
    public void load() throws IOException {
        // Create a new directory from current working directory
        File directory = new File(currWorkingDirectory + "/DukeSaveDirectory");

        // Create a new txt in filepath
        File txtFile = new File(currWorkingDirectory + filePath);

        // returns true if directory is created
        if (directory.mkdir()) {
            throw new IOException("Hmm kinda sussy you don't have the directory... it's aite, "
                    + "lemme help you with that.");
        }

        // returns true if file is created
        if (txtFile.createNewFile()) {
            throw new IOException("Hmm kinda sussy you don't have the txt save file... it's aite, "
                    + "lemme help you with that.");
        }

        readFileContents(txtFile.toString());
    }

    /**
     * Reads the task data from the txt file and formats it to be input into a new <code>Task</code> object.
     *
     * @param filePath the txt file that stores the task data.
     * @throws FileNotFoundException the file does not exist.
     */
    public static void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNextLine()) {
            String currentLine = s.nextLine();
            String[] lineArr = currentLine.split("\\|");
            int checkMarked = Integer.parseInt(lineArr[1]);
            String firstLetter = lineArr[0];
            switch (firstLetter) {
            case "T":
                readTodo(lineArr, checkMarked);
                break;
            case "D":
                readDeadline(lineArr, checkMarked);
                break;
            case "E":
                readEvent(lineArr, checkMarked);
                break;
            default:
                break;
            }
        }
    }

    /**
     * Checks whether the task in the task data is marked or not.
     *
     * @param markedNum 0 means unmarked, 1 means marked.
     * @return true if marked, else false.
     */
    public static boolean isMarked(int markedNum) {
        return markedNum == 1;
    }

    private static void readTodo(String[] strings, int num) {
        Task toDo = new ToDo(strings[2]);
        if (isMarked(num)) {
            toDo.mark();
        } else {
            toDo.unmark();
        }
        TaskList.addToListNoPrint(toDo);
    }

    private static void readDeadline(String[] strings, int num) {
        Task deadline = new Deadline(strings[DESCRIPTION], Parser.convertStringToLocalDate(strings[START_DATE]),
                Parser.convertStringToLocalTime(strings[START_TIME]));
        if (isMarked(num)) {
            deadline.mark();
        } else {
            deadline.unmark();
        }
        TaskList.addToListNoPrint(deadline);
    }

    private static void readEvent(String[] strings, int num) {
        Task event = new Event(strings[DESCRIPTION], Parser.convertStringToLocalDate(strings[START_DATE]),
                Parser.convertStringToLocalTime(strings[START_TIME]),
                Parser.convertStringToLocalTime(strings[END_TIME]));
        if (isMarked(num)) {
            event.mark();
        } else {
            event.unmark();
        }
        TaskList.addToListNoPrint(event);
    }
}
