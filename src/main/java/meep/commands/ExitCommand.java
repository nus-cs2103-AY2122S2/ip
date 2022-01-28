package meep.commands;

import meep.task.ListTask;


/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";


    /**
     * Returns boolean to check command is ExitCommand or not.
     *
     * @param command command to check.
     * @return is ExitCommand or not.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }


    /**
     * Returns successfully exited message.
     *
     * @param tasks task list.
     * @return successfully exited message.
     */
    @Override
    public String execute(ListTask tasks) {
        return MESSAGE_EXIT;
    }


    /**
     * Returns command word.
     *
     * @return command word.
     */
    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
