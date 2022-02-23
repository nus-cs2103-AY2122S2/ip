package sparrow.logic.commands;

import sparrow.logic.task.Deadline;
import sparrow.model.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;



/**
 * Represents the command to add a deadline.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final Deadline deadline;

    public DeadlineCommand(String description, String by) {
        deadline = new Deadline(description, by);
    }

    /**
     * Executes the command.
     *
     * @param storage  Storage of Duke.
     * @param tasks Task list of Duke.
     * @param ui User interface of Duke.
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(deadline);
        return ui.addTask(deadline) + ui.countTasks(tasks);
    }

    /**
     * Indicates whether the program should stop after this command.
     *
     * @return Boolean indicating whether the program should stop after this command.
     */
    public boolean isExit() {
        return false;
    }
}
