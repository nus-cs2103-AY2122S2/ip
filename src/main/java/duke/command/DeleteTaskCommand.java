package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.manager.Ui;
import duke.task.Task;

/**
 * Represents a Command that will delete a specified Task upon execution.
 */
public class DeleteTaskCommand extends Command {
    private int taskNo;

    /**
     * A constructor to store the index of the Task to be deleted.
     *
     * @param taskNo The index of the Task to be deleted.
     */
    public DeleteTaskCommand(int taskNo) {
        super();
        this.taskNo = taskNo;
    }

    /**
     * Executes the command by deleting the task at the index stored in this object.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param ui An Ui object to handle user interaction.
     * @param storage A Storage object to handle saving of data.
     * @throws DukeException If there is an issue saving the tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.delete(taskNo);
        ui.print("Noted. I've removed this task:");
        ui.print(task.toString());
        ui.print("Now you have " + taskList.numOfTasks() + " tasks in the list.");
        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Returns True if it is an exit command and false otherwise.
     * @return A boolean.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
