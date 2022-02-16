package duke.command;

import duke.operations.TaskList;

/**
 * Represents a subclass of Command.
 */
public class FindCommand extends Command {
    /**
     * A constructor for <code>FindCommand</code>.
     *
     * @param keyword the word to be searched in the arraylist of tasks.
     */
    public FindCommand(String keyword) {
        super(null, null, keyword);
    }

    /**
     * Finds the word in the task arraylist.
     *
     * @param taskList the task to be found in the arraylist.
     * @return the task to be printed out by GUI.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.findTaskInList(keyword);
    }
}
