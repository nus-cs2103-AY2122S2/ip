package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that lists all tasks with titles that match a user provided keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyword The keyword the user has provided to find matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Instructs UI object to display all tasks that match the keyword in a numbered list form.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object responsible for user interaction.
     * @param storage The Storage object responsible for saving the change.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayMatchingTasks(taskList.getTasksByKeyword(this.keyword));
    }
}
