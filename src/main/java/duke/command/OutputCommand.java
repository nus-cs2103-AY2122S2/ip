package duke.command;

import duke.Duke;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.MessageUi;

/**
 * Represents commands which outputs to the user. A MarkCommand
 * object corresponds a valid Ekud command, which can then be executed.
 */
public class OutputCommand implements Command {

    private String fullCommand;
    private String[] splitFullCommand;
    private String taskType;

    /**
     * Constructor for this class.
     * @param fullCommand User's input.
     */
    public OutputCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splitFullCommand = fullCommand.split(" ", 2);
        this.taskType = splitFullCommand[0];
    }

    /**
     * Executes a valid Ekud command that prints all the tasks in the
     * task list. If task list is empty, a message will be shown to inform
     * the user.
     *
     * @param tasks   Task object.
     * @param storage Storage object.
     * @param ui      Ui object.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, MessageUi ui) throws DukeException {
        if (tasks.getTaskSize() == 0) {
            throw new DukeException(ui.showEmptyListMessage());
        } else {
            return ui.showListMessage(tasks);
        }
    }
}
