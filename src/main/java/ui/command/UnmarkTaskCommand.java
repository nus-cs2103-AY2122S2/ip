package ui.command;

import task.Task;

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
    public boolean execute() throws IllegalArgumentException {
        // Args for this command represents index of task to
        // mark as undone
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(super.getArgs()) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Non-number passed to mark/unmark task");
        }

        if (taskIndex < 0 || taskIndex >= this.list.size()) {
            throw new IllegalArgumentException("Mark/unmark index out of list range");
        }

        Task task = this.list.get(taskIndex);
        task.unmarkDone();

        ArrayList<String> response = new ArrayList<>();
        response.add("The following task was marked undone:");
        response.add(task.getDescription());
        Command.styledPrint(response);
        return false;
    }
}
