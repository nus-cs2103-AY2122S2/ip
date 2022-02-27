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
     * @return The data file.
     */
    public File getTasks() {
        return this.storage;
    }

    /**
     * Appends task to the data file.
     *
     * @param task The task to be appended.
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
     * @param task The task to be edited.
     * @param id   1 to mark/unmark, 2 to delete the line from file.
     */
    public void editTasks(Task task, int id) throws FileNotFoundException, IOException {
        StringBuilder result = new StringBuilder();
        // This splits the modified task to help get the task info
        String[] modTaskInfo = task.toString().split(PLACE_TO_SPLIT_STRING);
        String modTaskDesc = modTaskInfo[1];
        Scanner sc = new Scanner(storage);
        while (sc.hasNext()) {
            // This is the task stored in the file
            String currStoredTask = sc.nextLine();
            String[] currTaskInfo = currStoredTask.split(PLACE_TO_SPLIT_STRING);
            String currTaskDesc = currTaskInfo[1];
            // Checks if the modified task info matches the stored task
            if (currTaskDesc.equals(modTaskDesc)) {
                idHandler(task, id, result);
            } else {
                result.append(currStoredTask).append("\n");
            }
        }

        // Clears all info from stored file. Uses result to fill in updated data
        FileWriter f = new FileWriter(FILE_PATH);
        f.write(result.toString());
        f.close();
    }

    private void idHandler(Task task, int id, StringBuilder result) {
        switch (id) {
        // For mark/unmark
        case 1:
            result.append(task).append("\n");
            break;
        // For delete, by skipping append
        case 2:
            break;
        default:
            // Assuming that the id is always 1 or 2 and no other value.
            assert false : "Id assumption has been breached";
        }
    }
}
