package duke.operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
     */
    public static void updateTextFile() {
        try {
            writeToFile(currWorkingDirectory + filePath);
        } catch (IOException e) {
            System.out.println("Cannot write to txt file!");
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
            System.out.println("Hmm kinda sussy you don't have the directory... it's aite, "
                    + "lemme help you with that.");
        }

        // returns true if file is created
        if (txtFile.createNewFile()) {
            System.out.println("Hmm kinda sussy you don't have the txt save file... it's aite, "
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
            switch (lineArr[0]) {
            case "T":
                Task toDo = new ToDo(lineArr[2]);
                if (isMarked(checkMarked)) {
                    toDo.mark();
                } else {
                    toDo.unmark();
                }
                TaskList.addToListNoPrint(toDo);
                break;
            case "D":
                Task deadline = new Deadline(lineArr[2], Parser.convertStringToLocalDate(lineArr[3]),
                        Parser.convertStringToLocalTime(lineArr[4]));
                if (isMarked(checkMarked)) {
                    deadline.mark();
                } else {
                    deadline.unmark();
                }
                TaskList.addToListNoPrint(deadline);
                break;
            case "E":
                Task event = new Event(lineArr[2], Parser.convertStringToLocalDate(lineArr[3]),
                        Parser.convertStringToLocalTime(lineArr[4]), Parser.convertStringToLocalTime(lineArr[5]));
                if (isMarked(checkMarked)) {
                    event.mark();
                } else {
                    event.unmark();
                }
                TaskList.addToListNoPrint(event);
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
}
