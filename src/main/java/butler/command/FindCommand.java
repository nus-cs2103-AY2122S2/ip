package butler.command;

import butler.TaskList;
import butler.util.Storage;
import butler.util.ui.Ui;

public class FindCommand extends Command {
    private String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Executes command behavior.
     * In this case, searches through the taskList to find a task whose description contains a
     * substring matching the searchString.
     *
     * @param taskList A managed list of tasks.
     * @param storage A handler class that handles file I/O, storing taskList details in a file.
     * @param ui A handler class that handles user input and output.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        String message = taskList.find(searchString);
        ui.setMessage(message);
    }
}
