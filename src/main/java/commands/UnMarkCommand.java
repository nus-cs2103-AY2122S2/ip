package commands;

import task.ListTask;
import task.Task;

public class UnMarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:\n";

    private final int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(ListTask tasks) {
        Task task = tasks.get(index);
        task.unmarkDone();
        return MESSAGE_UNMARK+task.toString();
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
