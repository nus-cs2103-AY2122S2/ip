package duke.task;

import duke.datetime.DateTable;
import duke.exception.BotException;
import duke.util.NumericCheck;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> storingList = new ArrayList<>();
    private final Storage botStorage;
    private int totalTask = 0;
    private final Ui ui;
    private final BotException exception = new BotException();
    private final DateTable dateTable;

    public TaskList() {
        this.botStorage = null;
        this.ui = null;
        this.dateTable = null;
    }

    public TaskList(Storage botStorage) {
        this.botStorage = botStorage;
        this.ui = null;
        this.dateTable = null;
    }

    public TaskList(Storage botStorage, Ui ui, DateTable dateTable) throws IOException {
        this.botStorage = botStorage;
        this.ui = ui;
        this.dateTable = dateTable;
        isFileHasText();
    }

    public boolean isFileHasText() throws IOException {
        if (!(botStorage.getDatabaseLength() == 0)) {
            botStorage.readFileContent(storingList);
            totalTask = storingList.size();
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Task> showStoringList() {
        return this.storingList;
    }


    public Task getTask(int taskNumber) {
        return storingList.get(taskNumber - 1);
    }

    public int getTotalTask() {
        return this.totalTask;
    }

    public void addTask(Task task) {
        storingList.add(task);
        totalTask += 1;
    }

    public Task removeTask(int taskNumber) {
        this.totalTask -= 1;
        return storingList.remove(taskNumber - 1);
    }

    public void deleteCommand(String taskNumberString) throws IOException {
        if (! NumericCheck.checkNumeric(taskNumberString)) {
            exception.notNumeric("delete");
        } else {
            int taskNumber = Integer.parseInt(taskNumberString);
            Task removeTask = storingList.remove(taskNumber - 1);
            this.totalTask -= 1;
            botStorage.deleteTask(taskNumber);
            ui.showDeleteTask(removeTask, this.totalTask);
        }
    }


}
