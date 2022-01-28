package Duke.command;

import Duke.exception.DukeException;

import Duke.task.Task;

import Duke.util.TaskList;
import Duke.util.Storage;
import Duke.util.Ui;

public class AddCommand extends Command {

    Task task;
    public static final String COMMAND_WORD = "add";

    /**
     * Constructor for AddCommand which adds the provided task.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes command by adding task into Duke.Duke.util.TaskList.
     *  @param taskList List of tasks
     * @param ui        Ui provided
     * @param storage   Saved history
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(task);
    }
}
