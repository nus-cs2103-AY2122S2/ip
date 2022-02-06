package duke.command;

import duke.operations.TaskList;

/**
 * Represents a subclass of Command.
 */
public class MarkCommand extends Command {
    /**
     * A constructor for <code>MarkCommand</code>.
     *
     * @param num the index in the arraylist to be marked.
     */
    public MarkCommand(Integer num) {
        super(null, num, null);
    }

    /**
     * Marks the task in the arraylist.
     *
     * @param taskList the task to be marked in the arraylist.
     * @return the task to be printed out by GUI.
     */
    @Override
    public String execute(TaskList taskList) {
        try {
            taskList.mark(num);
            String message = "The bar on the top left of your screen just increased! Keep going!" + "\n   "
                    + taskList;
            return message;
        } catch (IndexOutOfBoundsException e) {
            return "The task number is kinda sus... it's outta bounds!";
        }
    }
}
