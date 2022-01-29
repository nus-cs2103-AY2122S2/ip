package duke.command;

import duke.functionality.TaskList;

/**
 * Represents the find command. A <code>FindCommand</code> object corresponds to finding similar tasks
 * in the taskList of TaskList class.
 */
public class FindCommand extends Command {

    /**
     * Constructor of FindCommand.
     * @param word keyword used to find similar tasks in taskList of TaskList class.
     */
    public FindCommand(String word) {
        super(null, null, word);
    }

    /**
     * Returns nothing, but finds all task that share the same word in the taskList in TaskList class.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.findWord(super.word);
    }

    /**
     * Returns false as the Command is not an ExitCommand.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
