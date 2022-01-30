package duke.commands;

import java.io.IOException;

import duke.data.task.Task;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    /**
     * Index of the task to be deleted.
     */
    protected int index;

    /**
     * Constructs a delete command with the given index.
     *
     * @param index index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command
     *
     * @return response from the deletion to be printed.
     * @throws IOException if there is an error in removing the task from storage.
     */
    public String execute() throws IOException {
        Task deletedTask = super.taskList.deleteTask(this.index);
        String uuid = deletedTask.getId();

        // persist deletion
        super.storage.removeTask(uuid);

        return String.format("Noted. I've removed this task:%n%s%n"
                + "Now you have %d tasks in the list.%n", deletedTask, super.taskList.getSize());
    }
}
