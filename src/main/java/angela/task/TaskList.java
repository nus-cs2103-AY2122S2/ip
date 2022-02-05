package angela.task;

import java.io.IOException;
import java.util.ArrayList;

import angela.datetime.DateTable;
import angela.exception.BotException;
import angela.util.Storage;
import angela.util.Ui;

/**
 * Stores and performs operation on a collection of tasks
 */
public class TaskList {
    private final ArrayList<Task> storingList = new ArrayList<>();
    private final Storage botStorage;
    private int totalTask = 0;
    private final Ui ui;
    private final BotException exception = new BotException();
    private final DateTable dateTable;

    /**
     * Initialize an empty reference of task list, for testing purposes
     */
    public TaskList() {
        this.botStorage = null;
        this.ui = null;
        this.dateTable = null;
    }

    /**
     * Initialize an empty reference of task list with only storage, for testing purposes
     * @param botStorage The input storage reference
     */
    public TaskList(Storage botStorage) {
        this.botStorage = botStorage;
        this.ui = null;
        this.dateTable = null;
    }

    /**
     * Initialize the task-list object for Duke bot to use
     * @param botStorage The database for offline data storing
     * @param ui The ui interface that interact with user
     * @param dateTable Collections of dates and tasks
     * @throws IOException If an I/O exception occur
     */
    public TaskList(Storage botStorage, Ui ui, DateTable dateTable) throws IOException {
        this.botStorage = botStorage;
        this.ui = ui;
        this.dateTable = dateTable;
        isFileHasText();
    }

    /**
     * Checks whether the database file have any data yet
     *
     * @return True if the file has text and false otherwise
     * @throws IOException If an I/O error occur
     */
    public boolean isFileHasText() throws IOException {
        if (!(botStorage.getDatabaseLength() == 0)) {
            botStorage.readFileContent(storingList);
            totalTask = storingList.size();
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Task> getStoringList() {
        return this.storingList;
    }

    public Task getTask(int taskNumber) {
        return storingList.get(taskNumber - 1);
    }

    public int getTotalTask() {
        return this.totalTask;
    }

    /**
     * Adds the task into the storing list
     *
     * @param task The task need to be added
     */
    public void addTask(Task task) {
        storingList.add(task);
        totalTask += 1;
    }

    /**
     * Remove a specific task from the storing list
     *
     * @param taskNumber The task id
     * @return The removed <code>Task</code>
     */
    public Task removeTask(int taskNumber) {
        this.totalTask -= 1;
        return storingList.remove(taskNumber - 1);
    }

    /**
     * Finds tasks in the storing list that match with the keyword
     *
     * @param keyWord The keyword to find the matching task
     */
    public String findTasksByKeyWord(String keyWord) {
        String reply = "";
        int numIndex = 0;
        for (int i = 0; i < storingList.size(); i++) {
            Task task = storingList.get(i);
            if (isTaskHasKeyWord(task, keyWord)) {
                numIndex += 1;
                reply += ui.showSearchResult(numIndex, task);
            }
        }
        return reply;
    }

    /**
     * Checks if the task contains the keyword
     *
     * @param task The task needed to be checked
     * @param keyWord The required keyword in the task
     * @return True if the task contains keyword and false otherwise
     */
    private boolean isTaskHasKeyWord(Task task, String keyWord) {
        String taskString = task.toString();
        return taskString.contains(keyWord);
    }

}
