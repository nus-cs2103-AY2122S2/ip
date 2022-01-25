package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private static final boolean IS_EXIT = false;
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        super(IS_EXIT);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(taskIndex);
            tasks.delete(taskIndex);
            String message = String.format("%s\n  %s\nThere are %d tasks in the burning list.",
                    Ui.REMOVE_MESSAGE, task.toString(), tasks.getSize());
            ui.appendMessage(message);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
