package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.Storage;
import duke.managers.Ui;
import duke.tasks.TaskList;


/**
 * Represents a delete command recognized by the parser.
 * It stores the index of the task that is to be deleted. Upon
 * execution of the object, it will attempt to remove the indexed task from the
 * task list.
 */
public class DeleteCommand extends Command {
    protected int index;

    /**
     * Handles user input and stores the index of the task to be deleted.
     *
     * @param tokens a String array that represents the user input.
     * @throws DukeException when the input provided is not a valid index.
     */
    @Override
    public void handleParam(String[] tokens) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (Exception exception) {
            throw new DukeException("Invalid input! Please enter the number of the task you want to delete.");
        }
        this.index = index;
    }

    /**
     * Returns a boolean that specifies whether the user input matches the Command.
     *
     * @return a boolean that indicates whether this object is the correct Command.
     */
    @Override
    public boolean checkIdentifier(String input) {
        return input.equals("delete");
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
     * @throws DukeException when the index provided by the user is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        io.showMessage("Noted. I've removed this task:\n       "
                + taskList.remove(index)
                + "\n     Now you have " + taskList.getSize() + " task(s) in the list.");
        storage.save(taskList);
    }
}
