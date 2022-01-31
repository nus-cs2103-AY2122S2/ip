package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

/**
 * Deal with handling command for Filter.
 */
public class FilterCommand extends Command {
    String description;

    /**
     * The constructor for FilterCommand.
     *
     * @param description The string used to filter the list.
     * @return Returns a string to be displayed to the user.
     */
    public FilterCommand(String description) {
        super(false);
        this.description = description;
    }

    /**
     * Execute the command to filter the list.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     * @throws FunBoxExceptions If date is not formatted in `yyyy-mm-dd`.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions {
        return taskList.filterTasks(this.description, taskList, ui);
    }
}
