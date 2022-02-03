package duke.commands;

import duke.managers.Storage;
import duke.managers.Ui;
import duke.tasks.TaskList;

/**
 * Represents an exit command recognized by the parser.
 * Upon execution of the object, it will print a goodbye message and flip the boolean
 * to terminate the program.
 */
public class ExitCommand extends Command {

    /**
     * This command does not need any additional user input.
     *
     * @param tokens a String array that represents the user input.
     */
    @Override
    public void handleParam(String[] tokens) { }

    /**
     * Returns a boolean that specifies whether the user input matches the Command.
     *
     * @return a boolean that indicates whether this object is the correct Command.
     */
    @Override
    public boolean checkIdentifier(String input) {
        return input.equals("bye");
    }

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
