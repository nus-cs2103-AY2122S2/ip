package jeff.command;

import jeff.main.JeffException;

import jeff.storage.Storage;

import jeff.task.TaskList;

import jeff.ui.Ui;

/**
 * UnmarkCommand class is a Command that contains instructions
 * to run when user wants to mark a specific task as not done.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructor of UnmarkCommand.
     *
     * @param body Position of the task to mark as not done.
     */
    public UnmarkCommand(String body) {
        this.index = Integer.parseInt(body) - 1;
    }

    /**
     * Marks the Task as not done according
     * to index given by the user if available.
     *
     * @param tasks TaskList containing all the tasks.
     * @param ui Ui class for invoking user feedback.
     * @param storage Storage class used to save files.
     * @throws JeffException When index of task to mark as not done
     *                       is out of bounds or file cannot be saved.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        try {
            tasks.getAt(index);
        } catch (IndexOutOfBoundsException e) {
            int temp = index + 1;
            throw new JeffException(" ☹ OOPS!!! The task you want to mark as not done at index "
                    + temp + " is out of bounds,"
                    + "please double check the index number");
        }
        tasks.unmark(index);
        ui.showUnmark(tasks.getString(index));
        storage.save(tasks);
    }

    /**
     * Used to exit the Jeff program.
     *
     * @return false to keep running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
