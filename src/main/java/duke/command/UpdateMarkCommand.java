package duke.command;

import duke.datetime.DateTable;
import duke.exception.BotException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.NumericCheck;
import duke.util.BotStorage;
import duke.util.Ui;

import java.io.IOException;

public class UpdateMarkCommand extends duke.command.Command {
    private String description;
    private boolean isDone;
    private final BotException exception = new BotException();

    public UpdateMarkCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable)
            throws IOException {
        String stateDescription;
        if (isDone) {
            stateDescription = "mark";
        } else {
            stateDescription = "unmark";
        }

        if (! NumericCheck.checkNumeric(description)) {
            exception.notNumeric(stateDescription);
        } else {
            int taskNumber = Integer.parseInt(description);
            Task task = taskList.getTask(taskNumber);
            ui.showTaskMark(task, isDone);
            botStorage.changeStatusTask(taskNumber, task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
