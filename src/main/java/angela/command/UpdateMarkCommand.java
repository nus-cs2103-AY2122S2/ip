package angela.command;

import java.io.IOException;
import java.util.ArrayList;

import angela.datetime.DateTable;
import angela.exception.BotException;
import angela.task.Task;
import angela.task.TaskList;
import angela.util.BotStorage;
import angela.util.NumericChecker;
import angela.util.Ui;


/**
 * Updates the state of the task
 */
public class UpdateMarkCommand extends angela.command.Command {
    private final String description;
    private final boolean isDone;
    private final BotException exception = new BotException();

    /**
     * Initialize an Update Mark Command
     *
     * @param description The task id need to be updated
     * @param isDone True if we want to update the task as done, false otherwise
     */
    public UpdateMarkCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks the state of the task and update accordingly in <code>TaskList</code> and
     * in database file
     *
     * @param taskList   Reference of the <code>TaskList</code> object
     * @param ui         Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable  Reference of the <code>DateTable</code> object
     * @throws IOException If an I/O error occur
     */
    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable)
            throws IOException {
        String stateDescription = getTaskStatus();
        return printCommandOutput(taskList, ui, botStorage, stateDescription);
    }

    /**
     * Get status of the task
     * @return The status of the task
     */
    private String getTaskStatus() {
        if (isDone) {
            return "mark";
        } else {
            return "unmark";
        }
    }

    /**
     * Returns the display string of the task to GUI
     *
     * @param taskList   Reference of the <code>TaskList</code> object
     * @param ui         Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @return The output display string
     * @throws IOException If an I/O error occur
     */
    private ArrayList<String> printCommandOutput(
            TaskList taskList, Ui ui, BotStorage botStorage, String stateDescription)
            throws IOException {
        if (NumericChecker.isNumeric(description)) {
            int taskNumber = Integer.parseInt(description);
            assert taskNumber > -1 : "Task number should be a positive integer";
            Task task = taskList.getTask(taskNumber);
            botStorage.changeStatusTask(taskNumber, task);
            return ui.showTaskMark(task, isDone);
        } else {
            return exception.printNotNumericError(stateDescription);
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
