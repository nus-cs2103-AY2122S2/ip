package ui.command;

import task.Task;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which deletes a previously
 * added task.
 */
public class DeleteTaskCommand extends TaskCommand {

    public DeleteTaskCommand(String name, String args, ArrayList<Task> list, File dataFile) {
        super(name, args, list, dataFile);
    }

    @Override
    public boolean execute() throws IllegalArgumentException {
        ArrayList<Task> taskList = this.getTaskList();
        // Args for this command represents index of task to delete
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(super.getArgs()) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Non-number passed to delete task");
        }

        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IllegalArgumentException("Delete index out of list range");
        }

        Task deletedTask = taskList.remove(taskIndex);
        this.saveTasksToFile();

        ArrayList<String> response = new ArrayList<>();
        response.add("Noted. The following task has been deleted:");
        response.add(deletedTask.getDescription());
        response.add(String.format("You now have %d tasks!", taskList.size()));
        Command.styledPrint(response);
        return false;
    }
}
