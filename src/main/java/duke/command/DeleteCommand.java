package duke.command;

import duke.datetime.DateTable;
import duke.exception.BotException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.NumericCheck;
import duke.util.BotStorage;
import duke.util.Ui;

import java.io.IOException;

public class DeleteCommand extends duke.command.Command {
    private String description;
    private final BotException exception = new BotException();

    public DeleteCommand(String description) {
        this.description = description;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable) throws IOException {
        taskList.deleteCommand(description);
        if (! NumericCheck.checkNumeric(description)) {
            exception.notNumeric("delete");
        } else {
            int taskNumber = Integer.parseInt(description);
            Task removeTask = taskList.removeTask(taskNumber);
            botStorage.deleteTask(taskNumber);
            ui.showDeleteTask(removeTask, taskList.getTotalTask());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
