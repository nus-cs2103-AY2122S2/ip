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
 * Deletes a specific task
 */
public class DeleteTaskCommand extends angela.command.Command {
    private String description;
    private final BotException exception = new BotException();

    public DeleteTaskCommand(String description) {
        this.description = description;
    }


    /**
     * Checks the description of the command and delete a specified task
     * from <code>TaskList</code> and database file
     *
     * @param taskList   Reference of the <code>TaskList</code> object
     * @param ui         Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable  Reference of the <code>DateTable</code> object
     * @throws IOException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable)
            throws IOException {
        if (!NumericChecker.isNumeric(description)) {
            return exception.printNotNumericError("delete");
        } else {
            int taskNumber = Integer.parseInt(description);
            Task removeTask = taskList.removeTask(taskNumber);
            botStorage.deleteTask(taskNumber);
            dateTable.deleteTaskOnDate(removeTask);
            return ui.showDeleteTask(removeTask, taskList.getTotalTask());
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
