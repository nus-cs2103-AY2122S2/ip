package ui.command;

import task.Task;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which unmarks a previously
 * added task.
 */
public class UnmarkTaskCommand extends TaskCommand {

    public UnmarkTaskCommand(String name, String args, ArrayList<Task> list, File dataFile) {
        super(name, args, list, dataFile);
    }

    @Override
    public boolean execute() throws IllegalArgumentException {
        // Args for this command represents index of task to
        // mark as undone
        ArrayList<Task> taskList = this.getTaskList();
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
        task.unmarkDone();
        this.saveTasksToFile();

        ArrayList<String> response = new ArrayList<>();
        response.add("The following task was marked undone:");
        response.add(task.getDescription());
        Command.styledPrint(response);
        return false;
    }
}
