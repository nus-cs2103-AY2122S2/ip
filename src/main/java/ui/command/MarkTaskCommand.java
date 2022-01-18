package ui.command;

import task.*;
import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which marks a previously
 * added task as done.
 */
public class MarkTaskCommand extends Command {
    /**
     * Current task list for ChatBot
     */
    private final ArrayList<Task> list;

    public MarkTaskCommand(String name, String args, ArrayList<Task> list) {
        super(name, args);
        this.list = list;
    }

    @Override
    public boolean execute() {
        // Args for this command represents index of task to
        // mark as complete
        int taskIndex = Integer.parseInt(super.getArgs()) - 1;
        Task task = this.list.get(taskIndex);
        task.markDone();

        ArrayList<String> response = new ArrayList<>();
        response.add("Congrats! The following task was marked as done:");
        response.add(task.getDescription());
        Command.styledPrint(response);
        return false;
    }
}
