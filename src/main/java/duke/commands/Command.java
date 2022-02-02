package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
/**
 * Represents an abstract command recognized by the parser.
 */
public abstract class Command {

    protected boolean exitProgram = false;

    /**
     * Returns a boolean that specifies whether the user input matches the Command.
     *
     * @return a boolean that indicates whether this object is the correct Command.
     */
    public abstract boolean checkIdentifier(String input);

    /**
     * Handles user input and stores any details needed to execute the Command.
     *
     * @param tokens a String array that represents the user input.
     * @throws DukeException when the input provided for the Command is invalid.
     */
    public abstract void handleParam(String[] tokens) throws DukeException;

    /**
     * Executes the command object.
     *
     * @param taskList a container of existing tasks in the program.
     * @param io a manager that deals with interactions with the user.
     * @param storage a manager that deals with storing and loading of files.
     * @throws DukeException when user input conflicts with required params.
     */
    public abstract void execute(TaskList taskList, Ui io, Storage storage) throws DukeException;



    /**
     * Returns a boolean that specifies whether the program should end.
     *
     * @return isExit which is the switch that determines if the program ends.
     */
    public boolean getExit() {
        return exitProgram;
    }
}
