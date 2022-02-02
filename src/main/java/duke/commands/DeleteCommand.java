package duke.commands;

import duke.exceptions.OutOfRangeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Class which handles deletion of tasks in list
 */
public class DeleteCommand extends Command {
    protected Task deleted;
    private final int deleteIndex;
    private TaskList tasks;

    /**
     * Constructor for delete command
     * deletes a task based using index provided by user
     *
     * @param deleteIndex
     */
    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex - 1;

    }

    /**
     * Method to get the modified task list after command execution
     *
     * @return TaskList
     */
    @Override
    public TaskList getList() {
        return tasks;
    }

    /**
     * Method to see if command ends the main program loop
     *
     * @return true if it ends main program
     */
    @Override
    public boolean endsProgram() {
        return false;
    }

    /**
     * Method to execute the delete command
     * Deletes the task from the input index
     *
     * @param tasks   tasks list to be modified
     * @param ui      to help with printing of messages
     * @param storage To deal with saving of task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException {
        this.tasks = tasks;
        if (deleteIndex >= tasks.size()) {
            throw new OutOfRangeException();
        }
        deleted = tasks.get(deleteIndex);
        this.tasks.remove(deleteIndex);

//        ui.printFormatted(new String[]{
//            "Noted. I've removed this task:",
//            "  " + deleted,
//            "Now you have " + this.tasks.size() + " tasks in the list"});
        return "Noted. I've removed this task:\n" +
            "  " + deleted + "\n" +
            "Now you have " + this.tasks.size() + " tasks in the list";
    }
}
