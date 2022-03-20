package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Find Command.
 */
public class FindCommand extends Command {
    private String target;

    /**
     * Instantiates a FindCommand object with target String and commandArray.
     * @param target String Target for searching through the array.
     * @param commandArray String array from input command.
     */
    public FindCommand(String target, String[] commandArray) {
        super(commandArray);
        this.target = target;
    }

    /**
     * Calls findTask in using the taskList object.
     * @param taskList TaskList TaskList object.
     * @param ui Ui Ui object.
     * @param storage Storage Storage object.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.findTask(target);
    }

    /**
     * Compares equality of two FindCommand objects based on target String.
     *
     * @param otherFind FindCommand The other FindCommand object to be compared to.
     * @return boolean Shows whether the two objects are equal.
     */
    @Override
    public boolean equals(Object otherFind) {
        FindCommand otherFindCommand = (FindCommand) otherFind;
        return otherFindCommand.target.equals(this.target);
    }
}
