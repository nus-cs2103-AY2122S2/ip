package jeff.command;

import jeff.main.JeffException;
import jeff.note.Note;
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
     * Constructor of UnmarkCommand stores the index in index 0 format.
     *
     * @param body Position of the task to mark as not done.
     */
    public UnmarkCommand(String body) {
        this.index = Integer.parseInt(body) - 1;
    }

    /**
     * Marks the Task as not done according to index given by the user if available.
     *
     * @param tasks TaskList containing all the tasks.
     * @param notes Contains all the notes.
     * @param ui Ui class for invoking user feedback.
     * @param storage Storage class used to save files.
     * @return confirmation response that a task has been unmarked.
     * @throws JeffException When index of task to mark as not done
     *                       is out of bounds or file cannot be saved.
     */
    @Override
    public String execute(TaskList tasks, Note notes, Ui ui, Storage storage) throws JeffException {
        try {
            tasks.getAt(index);
        } catch (IndexOutOfBoundsException e) {
            int temp = index + 1;
            throw new JeffException(" ☹ OOPS!!! The task you want to mark as not done at index "
                    + temp + " is out of bounds,"
                    + "please double check the index number");
        }
        tasks.unmark(index);
        String response = ui.showUnmark(tasks.getString(index));
        storage.save(tasks);
        return response;
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
