package funbox.command;

import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

/**
 * Deal with handling command for List.
 */
public class ListCommand extends Command {

    /**
     * The constructor for ListCommand.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Execute the command to display tasks in the list.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printTasks(ui);
    }
}
