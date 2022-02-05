package angela.command;

import java.io.IOException;

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
    private String description;
    private boolean isDone;
    private final BotException exception = new BotException();

    /**
     * Initialize an Update Mark Command
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
    public String execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable)
            throws IOException {
        String stateDescription = "";

        if (isDone) {
            stateDescription = "mark";
        } else {
            stateDescription = "unmark";
        }

        if (!NumericChecker.isNumeric(description)) {
            return exception.printNotNumericError(stateDescription);
        } else {
            int taskNumber = Integer.parseInt(description);
            Task task = taskList.getTask(taskNumber);
            botStorage.changeStatusTask(taskNumber, task);
            return ui.showTaskMark(task, isDone);
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
