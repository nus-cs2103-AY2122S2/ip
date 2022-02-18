package jeff.command;

import jeff.main.JeffException;
import jeff.note.Note;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

/**
 * DeleteCommand class is a Command that contains instructions
 * to run when user wants to delete a specific task.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor of DeleteCommand stores the index in index 0 format.
     *
     * @param body Position of the task to delete.
     */
    public DeleteCommand(String body) {
        this.index = Integer.parseInt(body) - 1;
    }

    /**
     * Deletes the Task according to index given by the user if available.
     *
     * @param tasks TaskList containing all the tasks.
     * @param notes Contains all the notes.
     * @param ui Ui class for invoking user feedback.
     * @param storage Storage class used to save files.
     * @return confirmation response that a task has been deleted.
     * @throws JeffException When index of task to delete is
     *                       out of bounds or file cannot be saved.
     */
    @Override
    public String execute(TaskList tasks, Note notes, Ui ui, Storage storage) throws JeffException {
        try {
            tasks.getAt(index);
        } catch (IndexOutOfBoundsException e) {
            int temp = index + 1;
            throw new JeffException(" ☹ OOPS!!! The task you want to delete at index "
                    + temp + " is out of bounds,"
                    + "please double check the index number");
        }
        String response = ui.showDelete(tasks.getString(index), tasks.size() - 1);
        tasks.delete(index);
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
