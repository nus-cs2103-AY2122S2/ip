package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Represents an abstract command recognized by the parser.
 */
public abstract class Command {

    protected boolean exitProgram = false;
    protected boolean modifyData = false;

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
     * Executes the command object and returns a String which signifies
     * the response to the command.
     *
     * @param taskList a container of existing tasks in the program.
     * @return a String which signifies the response to the command.
     * @throws DukeException when user input conflicts with required params.
     */
    public abstract String execute(TaskList taskList) throws DukeException;

    /**
     * Returns a boolean that specifies whether the program should end.
     *
     * @return isExit which is the switch that determines if the program ends.
     */
    public boolean getExit() {
        return exitProgram;
    }

    /**
     * Returns a boolean that specifies whether the command modifies data.
     *
     * @return isExit which is the switch that determines if data should be saved to file.
     */
    public boolean getModify() {
        return modifyData;
    }
}
