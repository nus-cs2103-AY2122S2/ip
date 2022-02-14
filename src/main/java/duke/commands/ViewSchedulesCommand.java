package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ViewSchedulesCommand extends Command {
    private final String prefix;

    public ViewSchedulesCommand(String prefix) {
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            return "Here's are matching tasks on that day\n" + tasks.searchTime(prefix);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Your expression of time is not valid\n eg: 2022-01-27 or 2022/01/27";
        }
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ViewSchedulesCommand && ((ViewSchedulesCommand) o).prefix.equals(prefix);
    }
}
