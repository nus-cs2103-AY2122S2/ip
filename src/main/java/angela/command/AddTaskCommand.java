package angela.command;

import java.io.IOException;

import angela.datetime.DateTable;
import angela.exception.BotException;
import angela.task.Task;
import angela.task.TaskList;
import angela.util.BotStorage;
import angela.util.Ui;

/**
 * Adds task to the respective storage command
 */
public class AddTaskCommand extends Command {
    private final String description;
    private final String fullInput;
    private final String type;
    private final BotException exception = new BotException();

    /**
     * Initialize an Add Task Command
     * @param fullInput Full input from user
     * @param description The description of the task
     * @param type The type of the task
     */
    public AddTaskCommand(String fullInput, String description, String type) {
        this.description = description;
        this.fullInput = fullInput;
        this.type = type;
    }

    /**
     * Adds date and task into <code>DateTable</code> and appends the new task into the database
     *
     * @param dateTable  Reference of the <code>DateTable</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param task       The task need to be added
     * @throws IOException If an I/O error occurs
     */
    private void addTaskDatabase(DateTable dateTable, BotStorage botStorage, Task task,
            TaskList taskList) throws IOException {
        if (!task.getType().equals("T")) {
            dateTable.addDate(task);
        }
        botStorage.addTaskToDatabase(task);
        taskList.addTask(task);
    }

    /**
     * Creates the <code>Task</code> Object and adds to list of tasks and database.
     * Also prints out the task content and the number of tasks after added
     *
     * @param taskList   Reference of the <code>TaskList</code> object
     * @param ui         Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable  Reference of the <code>DateTable</code> object
     * @throws IOException If an I/O error occurs
     */
    @Override
    public String execute(TaskList taskList, Ui ui,
            BotStorage botStorage, DateTable dateTable) throws IOException {
        if (type.equals("T") && fullInput.length() == 4) {
            return exception.printEmptyDescriptionError("todo");
        } else if (type.equals("D") && fullInput.length() == 8) {
            return exception.printEmptyDescriptionError("deadline");
        } else if (type.equals("E") && fullInput.length() == 5) {
            return exception.printEmptyDescriptionError("event");
        } else {
            Task task = new Task(description, type);
            addTaskDatabase(dateTable, botStorage, task, taskList);
            return ui.showAllTask(task, taskList.getTotalTask());
        }
    }

    /**
     * Checks if the command is the exit command
     *
     * @return False as this is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
