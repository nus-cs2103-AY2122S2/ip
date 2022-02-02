package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.Storage;
import duke.tasks.TaskList;
import duke.managers.Ui;

/**
 * Represents an event command recognized by the parser.
 * Upon execution, it will attempt to store an event into
 * the task list.
 */
public class StoreEventCommand extends StoreCommand {

    /**
     * Returns a boolean that specifies whether the user input matches the Command.
     *
     * @return a boolean that indicates whether this object is the correct Command.
     */
    @Override
    public boolean checkIdentifier(String input) {
        return input.equals("event");
    }

    /**
     * Executes the StoreEventCommand object.
     *
     * @param taskList a container of existing tasks in the program, used for the creation
     *                 and addition of a event task into the task list.
     * @param io a manager that deals with interactions with the user, used to print
     *           notifications to the user.
     * @param storage a manager that deals with storing and loading of files, used to save
     *                changes to the task list to file.
     */
    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        io.showMessage("Got it. I've added this task:\n       "
                + taskList.addEventTask(tokens)
                + "\n     Now you have " + taskList.getSize() + " task(s) in the list.");
        super.execute(taskList, io, storage);
    }
}
