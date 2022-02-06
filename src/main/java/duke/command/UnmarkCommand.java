package duke.command;

import duke.operations.TaskList;

/**
 * Represents a subclass of Command.
 */
public class UnmarkCommand extends Command {
    /**
     * A constructor for <code>UnmarkCommand</code>.
     *
     * @param num the index in the arraylist to be marked.
     */
    public UnmarkCommand(Integer num) {
        super(null, num, null);
    }

    /**
     * Unmarks the task in the arraylist.
     *
     * @param taskList the task to be marked in the arraylist.
     * @return the task to be printed out by GUI.
     */
    @Override
    public String execute(TaskList taskList) {
        try {
            taskList.unmark(num);
            return "Surely you aren't the imposter... right??" + "\n   " + taskList;
        } catch (IndexOutOfBoundsException e) {
            return "The task number is kinda sus... it's outta bounds!";
        }
    }
}
