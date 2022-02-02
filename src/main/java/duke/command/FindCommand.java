package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the find command to find a task by searching for a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Class constructor.
     *
     * @param keyword The keyword used to find tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command to find tasks containing the keyword and prints them.
     *
     * @param taskList The current list of tasks.
     * @param ui The ui of the program.
     * @param storage The storage of the program.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String result = taskList.findTasksWithKeyword(this.keyword);
        ui.printOutput(result);
    }

    /**
     * Returns true if command is an ExitCommand, else returns false.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
