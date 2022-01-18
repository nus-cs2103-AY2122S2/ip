package ui.command;

import task.*;
import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which unmarks a previously
 * added task.
 */
public class UnmarkTaskCommand extends Command {
    /**
     * Current task list for ChatBot
     */
    private final ArrayList<Task> list;

    public UnmarkTaskCommand(String name, String args, ArrayList<Task> list) {
        super(name, args);
        this.list = list;
    }

    @Override
    public boolean execute() {
        // Args for this command represents index of task to
        // mark as undone
        int taskIndex = Integer.parseInt(super.getArgs()) - 1;
        Task task = this.list.get(taskIndex);
        task.unmarkDone();

        ArrayList<String> response = new ArrayList<>();
        response.add("The following task was marked undone:");
        response.add(task.getDescription());
        Command.styledPrint(response);
        return false;
    }
}
