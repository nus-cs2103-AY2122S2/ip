package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Lists out all tasks in the TaskList.
 */
public class ListCommand extends Command {
    private static final String EMPTY_LIST = "There are currently no tasks in your list!";
    /**
     * Executes the list command.
     *
     * @param tasks   the TaskList containing the current tasks
     * @param ui      the Ui of the chatbot
     * @param storage the storage of the chatbot
     * @return the result of executing the list command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return EMPTY_LIST;
        }

        return "Your outstanding tasks as of now are as listed:\n"
                + tasks.toString();
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
