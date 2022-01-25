package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a list command to output all tasks in the task list.
 */
public class ListCommand extends Command {

    public ListCommand() {}

    /**
     * Executes the list command and print out list.
     *
     * @param taskList The current list of tasks.
     * @param ui The ui of the program.
     * @param storage The storage of the program.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String listToPrint = taskList.getList();
        ui.printOutput(listToPrint);
    }

    /**
     * Returns whether this is an exit command.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
