package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.Storage;
import duke.tasks.TaskList;
import duke.managers.Ui;

/**
 * Represents a todo command recognized by the parser.
 * Upon execution, StoreTodoCommand objects will attempt to store a todo into
 * the task list.
 * StoreTodoCommand object stores the tokens which represents the input of the user.
 */
public class StoreTodoCommand extends StoreCommand {

    /**
     * Creates an instance of a StoreTodoCommand object.
     *
     * @param tokens represents the input that was given by the user
     */
    public StoreTodoCommand(String[] tokens) {
        super(tokens);
    }

    /**
     * Executes the StoreTodoCommand object.
     *
     * @param taskList a container of existing tasks in the program, used for the creation
     *                 and addition of a todo task into the task list.
     * @param io a manager that deals with interactions with the user, used to print
     *           notifications to the user.
     * @param storage a manager that deals with storing and loading of files, used to save
     *                changes to the task list to file.
     */
    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        io.showMessage("Got it. I've added this task:\n       "
                + taskList.addTodoTask(tokens)
                + "\n     Now you have " + taskList.getSize() + " task(s) in the list.");
        super.execute(taskList, io, storage);
    }
}
