package duke.command;

import duke.operations.TaskList;

/**
 * Represents a subclass of Command.
 */
public class PrintCommand extends Command {
    /**
     * A constructor for <code>PrintCommand</code>.
     */
    public PrintCommand() {
        super(null, null, null);
    }

    /**
     * Prints the tasks in the arraylist.
     *
     * @param taskList the tasks to be printed.
     * @return the task to be printed out by GUI.
     */
    @Override
    public String execute(TaskList taskList) {
        String firstMessage = "Here are the tasks in your device:";
        return firstMessage + "\n   " + taskList.toString();
    }
}
