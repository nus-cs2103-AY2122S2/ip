package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Find command. Finds all tasks which contain a specified string.
 */
public class FindCommand extends Command {

    private String str;

    /**
     * Constructs find command.
     *
     * @param str String to match.
     */
    public FindCommand(String str) {
        super();
        this.str = str;
    }

    /**
     * Finds all tasks which contain a specified string.
     *
     * @param tasks List to add task to.
     * @param ui Interface to display results to.
     * @param storage File storage of tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.find(str);
        ui.showMessage("Here are your matching tasks:");
        ui.showTasks(matchingTasks);
    }

}
