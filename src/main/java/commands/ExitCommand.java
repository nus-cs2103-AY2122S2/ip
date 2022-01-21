package commands;

import task.ListTask;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }

    @Override
    public String execute(ListTask tasks) {
        return MESSAGE_EXIT;
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
