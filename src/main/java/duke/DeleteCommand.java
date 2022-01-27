package main.java.duke;

import java.io.IOException;

public class DeleteCommand extends Command {

    int taskToDelete;

    /**
     * Constructs an instance of Delete Command.
     *
     * @param taskToDelete Integer position of task to delete in tasklist.
     */
    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    /**
     * Executes the instance of Delete Command.
     *
     * @param tasks Contains the task list.
     * @param ui Deals with interaction with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     */
    public void execute(TaskList tasks,Ui ui, Storage storage) {
        ui.showRemoveTask(tasks.getTask(taskToDelete), tasks.size() - 1);
        tasks.delete(taskToDelete);
        try {
            storage.writeToFile(tasks.getTaskArr());
        } catch (IOException e) {
            ui.showError("IOException");
        }
    }

    /**
     * Checks whether this command is the terminating command to Duke.
     *
     * @return False.
     */
    @Override
    public boolean isEnd() {
        return false;
    }
}
