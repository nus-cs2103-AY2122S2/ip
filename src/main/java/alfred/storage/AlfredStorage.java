package alfred.storage;

import alfred.exceptions.InvalidIndexException;
import alfred.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates the logic for managing the internal data states
 * of Alfred.
 */
public class AlfredStorage {
    private final ArrayList<Task> taskList;
    private final String dataPath;
    private final String folderPath;


    /**
     * Constructs an AlfredStorage object.
     *
     * @param dataPath Data path to where Alfred.txt file should be saved.
     */
    public AlfredStorage(String folderPath, String dataPath) {
        this.dataPath = dataPath;
        this.folderPath = folderPath;
        this.taskList = this.loadOrCreateTaskList();
    }

    /**
     * Returns a string representation of the list of tasks being
     * tracked by the object.
     *
     * @return String object representing the list of tasks.
     */
    public String listToString() {
        return this.listToString(this.taskList);
    }

    private String listToString(ArrayList<Task> taskList) {
        assert taskList != null;
        String out = "";
        for (int i = 1; i < taskList.size() + 1; i++) {
            out += i + ". " + taskList.get(i - 1).toString() + "\n";
        }
        return out;
    }

    /**
     * Updates internal data state to mark the defined task
     * as complete.
     *
     * @param taskId Integer as 0-indexed key.
     * @throws InvalidIndexException if index is out of bounds.
     */
    public void markTask(int taskId) throws InvalidIndexException {
        assert this.taskList != null;
        this.checkValidListIndex(taskId);
        this.taskList.get(taskId).markComplete();
        this.saveToFile();
    }

    /**
     * Updates internal data state to mark the defined task
     * as incomplete.
     *
     * @param taskId Integer as 0-indexed key.
     * @throws InvalidIndexException if index is out of bounds.
     */
    public void unmarkTask(int taskId) throws InvalidIndexException {
        assert this.taskList != null;
        this.checkValidListIndex(taskId);
        this.taskList.get(taskId).markIncomplete();
        this.saveToFile();
    }

    /**
     * Updates internal data state to delete the defined task.
     *
     * @param taskId Integer as 0-indexed key.
     * @throws InvalidIndexException if index is out of bounds.
     */
    public void deleteTask(int taskId) throws InvalidIndexException {
        assert this.taskList != null;
        this.checkValidListIndex(taskId);
        this.taskList.remove(taskId);
        this.saveToFile();
    }

    /**
     * Updates the internal data state to add a Task object.
     *
     * @param task Task object.
     */
    public void addTask(Task task) {
        assert this.taskList != null;
        this.taskList.add(task);
        this.saveToFile();
    }

    /**
     * Gets the string representation of a given task.
     *
     * @param taskId 0-indexed id of a task.
     * @return String representation of the task.
     * @throws InvalidIndexException If the index is out of bounds.
     */
    public String taskToString(int taskId) throws InvalidIndexException {
        this.checkValidListIndex(taskId);
        return this.taskList.get(taskId).toString();
    }

    /**
     * Returns a formatted string that lists all matching tasks
     * with a name that matches input text.
     *
     * @param text Input text to be matched with regex.
     * @return Formatted output list of tasks.
     */
    public String find(String text) {
        assert this.taskList != null;
        ArrayList<Task> matches = new ArrayList<Task>();

        // iterate through arraylist to find match
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            if (task.match(text)) {
                matches.add(task);
            }
        }

        // check to determine response
        if (matches.size() == 0) {
            return "None found.";
        }
        return this.listToString(matches);

    }


    private void checkValidListIndex(int taskId) throws InvalidIndexException {
        assert this.taskList != null;
        if (taskId >= this.taskList.size() || taskId < 0) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Returns a string that describes state of the task list.
     *
     * @return String description of task list.
     */
    public String summarizeList() {
        assert this.taskList != null;
        return "Now you have " + this.taskList.size() + " task(s) in the your list.";
    }

    private ArrayList<Task> loadOrCreateTaskList() {
        ArrayList<Task> taskList;
        try {
            taskList = this.loadFromFile();
        } catch (FileNotFoundException fe) {
            try { // create empty file
                this.createDataDirectoryAndFile();
                taskList = new ArrayList<Task>();
            } catch (IOException ioe) {
                System.out.println(
                        "Something went wrong trying to save the file: " + ioe.getMessage()
                                + "\nPlease restart.");
                return null;
            }
        }
        return taskList;
    }

    private ArrayList<Task> loadFromFile() throws FileNotFoundException {

        // load file
        File file = new File(this.dataPath);
        Scanner sc = new Scanner(file);

        // put into arraylist
        String line;
        Task task;
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            task = Task.parseSavedInput(line);
            taskList.add(task); // FIFO
        }
        return taskList;
    }

    private void createDataDirectoryAndFile() throws IOException {
        System.out.println("Alfred.txt not created. Will create at: " + this.dataPath);
        File directory = new File(this.folderPath);
        directory.mkdir();
        File output = new File(this.dataPath);
        output.createNewFile();
    }

    private void saveToFile() {
        FileWriter fw;
        // save to file
        try {
            fw = new FileWriter(this.dataPath);
            for (int i = 0; i < this.taskList.size(); i++) {
                if (i != 0) {
                    fw.write("\n");
                }
                fw.write(this.taskList.get(i).taskToSaveString());
            }
            fw.close();

        } catch (FileNotFoundException fe) { // create if needed
            System.out.println("Something went wrong trying to load the file: " + fe.getMessage());

        } catch (IOException ioe) {
            System.out.println(
                    "Something went wrong trying to save the file: " + ioe.getMessage());
        }
    }

}
