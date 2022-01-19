package ui.command;

import task.*;
import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which deletes a previously
 * added task.
 */
public class DeleteTaskCommand extends Command {
    /**
     * Current task list for ChatBot
     */
    private final ArrayList<Task> list;

    public DeleteTaskCommand(String name, String args, ArrayList<Task> list) {
        super(name, args);
        this.list = list;
    }

    @Override
    public boolean execute() throws IllegalArgumentException {
        // Args for this command represents index of task to delete
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(super.getArgs()) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Non-number passed to delete task");
        }

        if (taskIndex < 0 || taskIndex >= this.list.size()) {
            throw new IllegalArgumentException("Delete index out of list range");
        }

        Task deletedTask = this.list.remove(taskIndex);

        ArrayList<String> response = new ArrayList<>();
        response.add("Noted. The following task has been deleted:");
        response.add(deletedTask.getDescription());
        response.add(String.format("You now have %d tasks!", this.list.size()));
        Command.styledPrint(response);
        return false;
    }
}
