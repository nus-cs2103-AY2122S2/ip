package duke.task;

import duke.datetime.DateTable;
import duke.exception.BotException;
import duke.util.Storing;
import duke.util.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Stores and performs operation on a collection of tasks
 */
public class TaskList {
    private final ArrayList<Task> storingList = new ArrayList<>();
    private final Storing botStoring;
    private int totalTask = 0;
    private final Ui ui;
    private final BotException exception = new BotException();
    private final DateTable dateTable;

    public TaskList() {
        this.botStoring = null;
        this.ui = null;
        this.dateTable = null;
    }

    public TaskList(Storing botStoring) {
        this.botStoring = botStoring;
        this.ui = null;
        this.dateTable = null;
    }

    public TaskList(Storing botStorage, Ui ui, DateTable dateTable) throws IOException {
        this.botStoring = botStorage;
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
        if (!(botStoring.getDatabaseLength() == 0)) {
            botStoring.readFileContent(storingList);
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

}
