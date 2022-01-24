import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> storingList = new ArrayList<>();
    private final Storage storage;
    private int totalTask = 0;
    private final Ui ui;
    private final BotException exception = new BotException();
    private final DateTable dateTable;

    public TaskList() {
        this.storage = null;
        this.ui = null;
        this.dateTable = null;
    }

    private static boolean checkNumeric(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }

    public TaskList(Storage storage, Ui ui, DateTable dateTable) throws IOException {
         this.storage = storage;
         this.ui = ui;
         this.dateTable = dateTable;
         if (!(storage.getDatabaseLength() == 0)) {
             storage.readFileContent(storingList);
             totalTask = storingList.size();
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
        if (! checkNumeric(taskNumberString)) {
            exception.notNumeric("delete");
        } else {
            int taskNumber = Integer.parseInt(taskNumberString);
            Task removeTask = storingList.remove(taskNumber - 1);
            this.totalTask -= 1;
            storage.deleteTask(taskNumber);
            ui.showDeleteTask(removeTask, this.totalTask);
        }
    }


}
