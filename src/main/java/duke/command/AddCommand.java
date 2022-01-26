package duke.command;

import duke.datetime.DateTable;
import duke.exception.BotException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.BotStoring;
import duke.util.Ui;

import java.io.IOException;

/**
 * Adds task to the respective storage command
 */
public class AddCommand extends Command {
    private final String description;
    private final String fullInput;
    private final String type;
    private final BotException exception = new BotException();

    public AddCommand(String fullInput, String description, String type) {
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
    private void addTaskDatabase(DateTable dateTable, BotStoring botStorage, Task task,
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
    public void execute(TaskList taskList, Ui ui,
            BotStoring botStorage, DateTable dateTable) throws IOException {
        if (type.equals("T") && fullInput.length() == 4) {
            exception.printEmptyDescriptionError("todo");
        } else if (type.equals("D") && fullInput.length() == 8) {
            exception.printEmptyDescriptionError("deadline");
        } else if (type.equals("E") && fullInput.length() == 5) {
            exception.printEmptyDescriptionError("event");
        } else {
            Task task = new Task(description, type);
            addTaskDatabase(dateTable, botStorage, task, taskList);
            ui.showAllTask(task, taskList.getTotalTask());
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
