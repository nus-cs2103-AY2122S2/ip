package duke.command;

import duke.datetime.DateTable;
import duke.exception.BotException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.BotStoring;
import duke.util.NumericCheck;
import duke.util.Ui;

import java.io.IOException;

/**
 * Deletes a specific task
 */
public class DeleteCommand extends duke.command.Command {
    private String description;
    private final BotException exception = new BotException();

    public DeleteCommand(String description) {
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
    public void execute(TaskList taskList, Ui ui, BotStoring botStorage, DateTable dateTable)
            throws IOException {
        if (!NumericCheck.isNumeric(description)) {
            exception.printNotNumericError("delete");
        } else {
            int taskNumber = Integer.parseInt(description);
            Task removeTask = taskList.removeTask(taskNumber);
            botStorage.deleteTask(taskNumber);
            ui.showDeleteTask(removeTask, taskList.getTotalTask());
            dateTable.deleteTaskOnDate(removeTask);
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
