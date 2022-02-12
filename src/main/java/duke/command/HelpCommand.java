package duke.command;

import duke.task.TaskList;

/**
 * A Command that returns a list of valid commands when executed.
 */
public class HelpCommand extends Command {

    /**
     * Returns a list of valid commands.
     * @param tasks current list of tasks
     * @return the help message
     */
    @Override
    public String execute(TaskList tasks) {
        return "You can type the following commands:\n"
                + "  1. list\n"
                + "  2. todo [description]\n"
                + "  3. deadline [description] /by [deadline]\n"
                + "  4. event [description] /at [time]\n"
                + "  5. mark [index]\n"
                + "  6. unmark [index]\n"
                + "  7. delete [index]\n"
                + "  8. edit [index] [syntax of 2, 3, or 4]\n"
                + "  9. find [keyword]\n"
                + " 10. bye";
    }

    /**
     * Indicates that the program should not be exited.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
