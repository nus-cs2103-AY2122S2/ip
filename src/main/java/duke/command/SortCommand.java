package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Sort Command, in charge of sorting Tasks based on given set of sorting types.
 */
public class SortCommand extends Command {
    private TaskList.SortType type;

    /**
     *
     * @param type SortType Sorting standard.
     * @param commandArray String[] String array from input command.
     */
    public SortCommand(TaskList.SortType type, String[] commandArray) {
        super(commandArray);
        this.type = type;
    }

    /**
     * Executes the sortTask from taskList and saves the file.
     *
     * @param taskList TaskList input taskList object from duke.Duke.
     * @param ui Ui input ui object from duke.Duke.
     * @param storage Storage input storage object from duke.Duke.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.sortTaskList(this.type);
        storage.save(taskList);
    }

    /**
     * Checks the equality of two Command objects based on sortType comparison.
     *
     * @param command Object Another command object to be compared to.
     * @return boolean Boolean value to show if this equals command.
     */
    @Override
    public boolean equals(Object command) {
        SortCommand sortCommand = (SortCommand) command;
        return this.type.equals(sortCommand.type);
    }
}
