package ui.command;

import data.TaskList;
import task.Task;

import java.util.ArrayList;

/**
 * Command which marks a previously
 * added task as done.
 */
class MarkTaskCommand extends TaskListCommand {

    public MarkTaskCommand(String name, String args, TaskList taskList) {
        super(name, args, taskList);
    }

    @Override
    public boolean execute() throws IllegalArgumentException {
        // Args for this command represents index of task to
        // mark as complete
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(super.getArgs()) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Non-number passed to mark/unmark task");
        }

        TaskList taskList = this.getTaskList();
        Task checkedTask = taskList.checkTask(taskIndex);

        ArrayList<String> response = new ArrayList<>();
        response.add("Congrats! The following task was marked as done:");
        response.add(checkedTask.getDescription());
        Command.styledPrint(response);
        return false;
    }
}
