package duke.command;

import duke.exception.DukeException;

import duke.task.Task;

import duke.util.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * This AddCommand class will add a task into a list of tasks when executed.
 */
public class AddCommand extends Command {

    private final Task task;
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
     * @param taskList  List of tasks
     * @param ui        Ui provided
     * @param storage   Saved history
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(task);
        String contentToSave = task.toSave() + "\n";
        Storage.append(contentToSave);
    }
}
