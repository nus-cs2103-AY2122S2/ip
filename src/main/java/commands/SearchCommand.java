package commands;

import data.TaskList;
import storage.Storage;
import ui.Ui;

public class SearchCommand extends Command{
    private final String prefix;

    public SearchCommand(String prefix) {
        super();
        this.prefix = prefix;
    }

    /**
     * Search tasks in the list that matches the prefix.
     * @param tasks The list containing all the tasks
     * @param ui User interface
     * @param storage Class that manages storage
     **/
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.respond("Here's are matching tasks in your list\n" + tasks.search(prefix));
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof SearchCommand && ((SearchCommand) o).prefix.equals(prefix);
    }
}
