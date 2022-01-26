package duke.command;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class DeleteCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final int taskId;

    public DeleteCommand(int taskId) {
        super(IS_EXIT);
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            tasks.deleteTask(taskId);
            ui.showExecutionMessage(Messages.MESSAGE_DELETE);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
