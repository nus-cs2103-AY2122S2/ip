package duke.chatbot.command;

import java.util.ArrayList;

import duke.data.TaskList;
import duke.task.Task;

/**
 * Command which deletes a previously
 * added task.
 */
public class DeleteTaskCommand extends TaskListCommand {

    /** Keeps track of task deleted by the command */
    private Task deletedTask;

    public DeleteTaskCommand(String name, String args, TaskList taskList) {
        super(name, args, taskList);
    }

    /**
     * Deletes a task using the index of task
     * provided as an argument.
     *
     * @return ArrayList containing string description of task deleted,
     * and number of tasks remaining.
     * @throws IllegalArgumentException If invalid index is given.
     */
    @Override
    public ArrayList<String> execute() throws IllegalArgumentException {
        // Args for this command represents index of task to delete
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(super.getArgs()) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Non-number passed to delete task");
        }

        TaskList taskList = this.getTaskList();
        Task deletedTask = taskList.deleteTask(taskIndex);
        this.deletedTask = deletedTask;

        ArrayList<String> response = new ArrayList<>();
        response.add("Noted. The following task has been deleted:");
        response.add(deletedTask.getDescription());
        response.add(String.format("You now have %d tasks!", taskList.getSize()));
        return response;
    }

    /**
     * Adds back previously deleted task.
     */
    @Override
    public void undo() {
        TaskList taskList = super.getTaskList();
        taskList.addTask(deletedTask);
    }
}
