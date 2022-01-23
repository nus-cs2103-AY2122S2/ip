package ui.command;

import task.Task;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which marks a previously
 * added task as done.
 */
public class MarkTaskCommand extends TaskCommand {

    public MarkTaskCommand(String name, String args, ArrayList<Task> list, File dataFile) {
        super(name, args, list, dataFile);
    }

    @Override
    public boolean execute() throws IllegalArgumentException {
        ArrayList<Task> taskList = this.getTaskList();
        // Args for this command represents index of task to
        // mark as complete
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(super.getArgs()) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Non-number passed to mark/unmark task");
        }

        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IllegalArgumentException("Mark/unmark index out of list range");
        }

        Task task = taskList.get(taskIndex);
        task.markDone();
        this.saveTasksToFile();

        ArrayList<String> response = new ArrayList<>();
        response.add("Congrats! The following task was marked as done:");
        response.add(task.getDescription());
        Command.styledPrint(response);
        return false;
    }
}
