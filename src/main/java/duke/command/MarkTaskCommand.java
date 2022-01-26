package duke.command;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class MarkTaskCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final boolean isDone;
    private final int taskId;

    public MarkTaskCommand(int taskId, boolean isDone) {
        super(IS_EXIT);
        this.taskId = taskId;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            tasks.markTask(taskId, isDone);
            String message;
            if (isDone) {
                message = Messages.MESSAGE_MARK;
            } else {
                message = Messages.MESSAGE_UNMARKED;
            }
            ui.showExecutionMessage(message);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
