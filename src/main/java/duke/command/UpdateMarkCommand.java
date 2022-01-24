package duke.command;

import duke.datetime.DateTable;
import duke.exception.BotException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
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

    private static boolean checkNumeric(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, DateTable dateTable)
            throws IOException {
        String stateDescription;
        if (isDone) {
            stateDescription = "mark";
        } else {
            stateDescription = "unmark";
        }

        if (! checkNumeric(description)) {
            exception.notNumeric(stateDescription);
        } else {
            int taskNumber = Integer.parseInt(description);
            Task task = taskList.getTask(taskNumber);
            ui.showTaskMark(task, isDone);
            storage.changeStatusTask(taskNumber, task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
