package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command for finding a Task that matches specified keywords.
 */
public class FindCommand extends Command {

    /** The message of the user in array format */
    private final String[] tokens;

    /**
     * Constructor of the class.
     *
     * @param tokens The message of the user in array format.
     */
    public FindCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * Executes the finding function to retrieve tasks which match the given keywords.
     *
     * @param tl The TaskList from each task will be added/deleted.
     * @param sge The Storage which stores/removes tasks from the hard-disk.
     * @return A String of the tasks found.
     */
    @Override
    public String execute(TaskList tl, Storage sge) {
        return tl.checkWordsInTask(this.tokens).length() == 0 ? "Chi couldn't find anything nyan!"
                : "H..H..Here's what I could find!\n" + tl.checkWordsInTask(this.tokens);
    }
}
