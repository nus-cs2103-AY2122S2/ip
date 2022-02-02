package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.MessageUi;

/**
 * Represents commands which deletes task to the task list. DeleteCommand
 * object corresponds a valid Ekud command, which can then be executed.
 */

public class DeleteCommand implements Command {

    private String fullCommand;
    private String[] splitFullCommand;
    private int index;

    /**
     * Constructor for this class.
     * @param fullCommand User's input.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splitFullCommand = this.fullCommand.split(" ", 2);
        this.index = Integer.parseInt(splitFullCommand[1]);
    }

    /**
     * Executes a valid Ekud command that delete a task from the task list.
     * The position of which the task is supplied by the user, of which
     * if the postion is < 1 or more than the number of tasks in the task list,
     * a DukeException would be thrown.
     *
     * @param tasks   Task object.
     * @param storage Storage object.
     * @param ui      Ui object.
     * @throws DukeException If position < 1 or more than number of tasks in task list.
     * @throws IOException   If directory or file cannot be found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage,
                        MessageUi ui) throws DukeException {
        if (index < 0 || index > tasks.getTaskSize()) {
            throw new DukeException("Task do not exist!");
        } else {
            Task task = tasks.getTask(index);
            tasks.removeTask(index);
            storage.writeToFile(tasks.getTaskList());
            return ui.showDeleteMessage(task, tasks.getTaskSize());
        }
    }
}
