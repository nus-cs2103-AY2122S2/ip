package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.Storage;
import duke.tasks.TaskList;
import duke.managers.Ui;

/**
 * Represents a event command recognized by the parser.
 * Upon execution, StoreEventCommand objects will attempt to store a event into
 * the task list.
 * StoreEventCommand object stores the tokens which represents the input of the user.
 */
public class StoreEventCommand extends StoreCommand {

    /**
     * Creates an instance of a StoreEventCommand object.
     *
     * @param tokens represents the input that was given by the user
     */
    public StoreEventCommand(String[] tokens) {
        super(tokens);
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
                + taskList.addEventTask(tokens).toString()
                + "\n     Now you have " + taskList.getSize() + " task(s) in the list.");
        super.execute(taskList, io, storage);
    }
}
