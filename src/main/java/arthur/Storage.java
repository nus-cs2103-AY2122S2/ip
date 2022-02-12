package arthur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import arthur.task.Task;

/**
 * Handles creating and editing data file.
 */
public class Storage {
    private static final String FILE_PATH = "data/Tasks.txt";
    private static final String IO_ISSUE_MESSAGE = "Sorry, there seems to be an Issue. \n"
            + "Please restart and try again";
    private static final String IO_FILE_ISSUE_MESSAGE = "Sorry, there seems to be an Issue "
            + "with adding the task to the file. \n" + "Please restart and try again";
    private static final String PLACE_TO_SPLIT_STRING = " >> ";
    private final File storage;

    /**
     * Gets the ui and creates the necessary file and directories.
     */
    public Storage() {
        new File("data").mkdirs();
        storage = new File(FILE_PATH);
        if (!storage.exists()) {
            try {
                storage.createNewFile();
            } catch (IOException e) {
                System.out.println(IO_ISSUE_MESSAGE);
            }
        }
    }

    /**
     * Gives the file stored in hard drive.
     *
     * @return The data file
     */
    public File getTasks() {
        return this.storage;
    }

    /**
     * Appends task to the data file
     *
     * @param task The task to be appended
     */
    public void addTasks(Task task) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(task.toString() + "\n");
            fw.close();
        } catch (IOException ex) {
            System.out.println(IO_FILE_ISSUE_MESSAGE);
        }
    }

    /**
     * Edits the tasks in the data file.
     *
     * @param task The task to be edited
     * @param id   1 to mark/unmark, 2 to delete the line from file
     */
    public void editTasks(Task task, int id) throws FileNotFoundException, IOException {
        StringBuilder tempData = new StringBuilder();
        // This splits the modified task to help get the task info
        String[] tempArr = task.toString().split(PLACE_TO_SPLIT_STRING);
        Scanner sc = new Scanner(storage);
        while (sc.hasNext()) {
            // This is the task stored in the file
            String curr = sc.nextLine();
            String[] currArr = curr.split(PLACE_TO_SPLIT_STRING);
            // Checks if the modified task info matches the stored task
            if (currArr[1].equals(tempArr[1])) {
                switch (id) {
                // For mark/unmark
                case 1:
                    tempData.append(task).append("\n");
                    break;
                // For delete, by skipping append
                case 2:
                    break;
                default:
                    break;
                }
            } else {
                tempData.append(curr).append("\n");
            }
        }

        // Clears all info from stored file. Uses tempData to fill in updated data
        FileWriter f = new FileWriter(FILE_PATH);
        f.write(tempData.toString());
        f.close();
    }
}
