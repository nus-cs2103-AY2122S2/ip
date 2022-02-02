package Helper;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import Tasks.Task;

/**
 * <h1>Storage</h1>
 * <p>
 * Storage class handles the storing of tasks from the current run,
 * and loading of tasks from the previous runs.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class Storage {

    // stores the location of previous task file.
    private static final String PATH_TASK_FILE = "taskList.txt";

    // the index of array list that stores the username.
    private static final int USER_INDEX = 0;

    // stores empty string.
    private static final String EMPTY_STR = "";

    // a file object.
    private final File file;

    // tell of there is a file already present.
    private final boolean isTaskFilePresent;

    // stores the username and previous tasks from the file.
    private final ArrayList<String> previousTasks;

    /**
     * Constructor for Storage class.
     * returns an instance of storage class.
     */
    public Storage() {
        this.file = new File(PATH_TASK_FILE);
        this.isTaskFilePresent = this.file.exists();
        this.previousTasks = new ArrayList<>();
    }

    /**
     * reads the file contents and stores each line in previousTasks.
     *
     * @throws FileNotFoundException raise an error if the file is not found
     */
    private void readFile() throws FileNotFoundException {

        if (this.isTaskFilePresent) {
            Scanner sc = new Scanner(this.file);

            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                this.previousTasks.add(str);
            }
        }

    }

    /**
     * gets the previous userName.
     *
     * @return the name of the previous user
     */
    public String getPreviousUser() {
        try {
            readFile();
            // the first line of the file contains the username.
            return this.previousTasks.remove(USER_INDEX).trim();
        } catch (FileNotFoundException e) {
            return EMPTY_STR;
        }
    }

    /**
     * tells if there was a file from previous runs.
     *
     * @return returns true of there was a file from previous runs; false otherwise.
     */
    public boolean isFilePresent() {
        return this.isTaskFilePresent;
    }

    /**
     * loads the previous tasks in string representation.
     *
     * @return the list of previous tasks.
     */
    public ArrayList<String> loadPreviousTasks() {
        return this.previousTasks;
    }

    /**
     * stores the tasks in the file.
     *
     * @param tasks the list of tasks the user told conan.
     * @param username the name of the user.
     */
    public void storeTasks(ArrayList<Task> tasks, String username) {
        try {

            boolean noPreviousFile = this.file.createNewFile();

            String content = username + "\n";

            for (Task task : tasks) {
                content += task.toString() + "\n";
            }

            FileWriter writer = new FileWriter(PATH_TASK_FILE);
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * stores the tasks in the file.
     *
     * @param tasks the list of tasks the user told conan.
     */
    public void storeTasks(String tasks) {
        try {

            boolean noPreviousFile = this.file.createNewFile();

            FileWriter writer = new FileWriter(PATH_TASK_FILE);
            writer.write(tasks);
            writer.close();

        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }
}
