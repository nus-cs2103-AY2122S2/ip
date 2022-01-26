package duke.commands;

import duke.tasks.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
/**
 * Represents a exit command recognized by the parser.
 * Upon execution of the object, it will print a goodbye message and flip the boolean
 * to terminate the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand object.
     *
     * @param taskList a container of existing tasks in the program.
     * @param io a manager that deals with interactions with the user, used to
     *           print the goodbye message.
     * @param storage a manager that deals with storing and loading of files.
     */
    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) {
        io.bye();
        exitProgram = true;
    }
}
