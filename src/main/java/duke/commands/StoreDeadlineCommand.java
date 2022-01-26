package duke.commands;

import duke.managers.Storage;
import duke.tasks.TaskList;
import duke.managers.Ui;
import duke.exceptions.DukeException;

/**
 * Represents a deadline command recognized by the parser.
 * Upon execution, StoreDeadlineCommand objects will attempt to store a deadline into
 * the task list.
 * StoreDeadlineCommand object stores the tokens which represents the input of the user.
 */
public class StoreDeadlineCommand extends StoreCommand {

    /**
     * Creates an instance of a StoreDeadlineCommand object.
     *
     * @param tokens represents the input that was given by the user
     */
    public StoreDeadlineCommand(String[] tokens) {
        super(tokens);
    }

    /**
     * Executes the StoreDeadlineCommand object.
     *
     * @param taskList a container of existing tasks in the program, used for the creation
     *                 and addition of a deadline task into the task list.
     * @param io a manager that deals with interactions with the user, used to print
     *           notifications to the user.
     * @param storage a manager that deals with storing and loading of files, used to save
     *                changes to the task list to file.
     */
    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        io.showMessage("Got it. I've added this task:\n       "
                + taskList.addDeadlineTask(tokens)
                + "\n     Now you have " + taskList.getSize() + " task(s) in the list.");
        super.execute(taskList, io, storage);
    }
}
