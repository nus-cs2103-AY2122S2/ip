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
     * @param tasks the task to be found in the arraylist.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.findTaskInList(keyword);
    }

    /**
     * Checks whether it is the exit command.
     *
     * @return if false, continue receiving input, else terminate.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
