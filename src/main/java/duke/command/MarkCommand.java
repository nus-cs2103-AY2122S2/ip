package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Responsible for the functionality needed when marking a task.
 */
public class MarkCommand extends Command {

    /** 1-based index of the task in the list. */
    private int point;

    /**
     * Constructor to create a Mark Command.
     *
     * @param point 1-based index of the task in the list.
     */
    public MarkCommand(int point) {
        super(CommandType.MARK);
        this.point = point;
    }

    /**
     * Marks the task as done, saves changes to file and prints ui message.
     *
     * @param taskList list of tasks.
     * @param ui user interface of the chat bot.
     * @param storage storage used by chat bot.
     * @throws DukeException if File IO exception is thrown when saving file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(point - 1);
        task.markAsDone();
        storage.saveToFile(taskList);
        ui.showMessage("Great! I have marked this task as done:\n " + task);
    }
}
