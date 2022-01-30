package duke.command;

import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private final String match;

    public FindCommand(String match) {
        this.match = match;
    }

    /**
     * Executes a method from taskList to find a matching
     * task with the word from the task.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String statement = "Here are the matching tasks in your list:\n";
        System.out.println(statement + taskList.findMatching(match));
    }

    /**
     * Returns false for non-Exit commands.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
