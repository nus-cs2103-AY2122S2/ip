package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Responsible for the functionality needed when deleting a task.
 */
public class DeleteCommand extends Command {

    /** 1-based index of task to be deleted. */
    private int point;

    /**
     * Constructor to create Delete Command.
     *
     * @param point 1-based index of task to be deleted.
     */
    public DeleteCommand(int point) {
        super(CommandType.DELETE);
        this.point = point;
    }

    /**
     * Deletes tasks pointed out, saves the task list and prints
     * out appropriate response message.
     *
     * @param taskList list of tasks.
     * @param ui user interface of the chat bot.
     * @param storage storage used by chat bot.
     * @throw DukeException if there is a File I/O exception thrown when saving file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.remove(point - 1);
        storage.saveToFile(taskList);
        ui.showMessage("Noted. I've removed this task:\n " + task
                + "\n" + taskList.sizeDescription());
    }
}
