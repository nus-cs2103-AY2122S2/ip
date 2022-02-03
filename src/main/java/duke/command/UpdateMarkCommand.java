package duke.command;

import java.io.IOException;

import duke.datetime.DateTable;
import duke.exception.BotException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.BotStorage;
import duke.util.NumericChecker;
import duke.util.Ui;


/**
 * Updates the state of the task
 */
public class UpdateMarkCommand extends duke.command.Command {
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
    public void execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable)
            throws IOException {
        String stateDescription = "";

        if (isDone) {
            stateDescription = "mark";
        } else {
            stateDescription = "unmark";
        }

        if (!NumericChecker.isNumeric(description)) {
            exception.printNotNumericError(stateDescription);
        } else {
            int taskNumber = Integer.parseInt(description);
            Task task = taskList.getTask(taskNumber);
            ui.showTaskMark(task, isDone);
            botStorage.changeStatusTask(taskNumber, task);
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
