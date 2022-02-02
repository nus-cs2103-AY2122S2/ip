package duke.chatbot.command;

import duke.data.TaskList;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Command which unmarks a previously
 * added task.
 */
class UnmarkTaskCommand extends TaskListCommand {

    public UnmarkTaskCommand(String name, String args, TaskList taskList) {
        super(name, args, taskList);
    }

    /**
     * Marks a given task in taskList as undone,
     * using index passed by user.
     *
     * @return ArrayList containing string description of tasked unmarked.
     * @throws IllegalArgumentException If the index is invalid.
     */
    @Override
    public ArrayList<String> execute() throws IllegalArgumentException {
        // Args for this command represents index of task to
        // mark as undone
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(super.getArgs()) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Non-number passed to mark/unmark task");
        }

        TaskList taskList = this.getTaskList();
        Task uncheckedTask = taskList.uncheckTask(taskIndex);

        ArrayList<String> response = new ArrayList<>();
        response.add("The following task was marked undone:");
        response.add(uncheckedTask.getDescription());
        return response;
    }
}
