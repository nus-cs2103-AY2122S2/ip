package duke.commands;

import duke.tasks.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
import duke.exceptions.DukeException;
/**
 * Represents a delete command recognized by the parser.
 * DeleteCommand object stores the index of the task that is to be deleted. Upon
 * execution of the object, it will attempt to remove the indexed task from the
 * task list.
 */
public class DeleteCommand extends Command {
    protected int index;

    /**
     * Creates an instance of a DeleteCommand object.
     *
     * @param index the index of the task that is to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand object.
     *
     * @param taskList a container of existing tasks in the program, used to
     *                 acquire the task to be deleted.
     * @param io a manager that deals with interactions with the user,
     *           used to print notifications to the user.
     * @param storage a manager that deals with storing and loading of files,
     *                used to save changes to taskList to file.
     */
    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        io.showMessage("Noted. I've removed this task:\n       "
                + taskList.remove(index).toString()
                + "\n     Now you have " + taskList.getSize() + " task(s) in the list.");
        storage.save(taskList);
    }
}
