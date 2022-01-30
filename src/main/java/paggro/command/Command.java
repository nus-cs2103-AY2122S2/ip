package paggro.command;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.storage.Storage;
import paggro.ui.Ui;

/**
 * This abstract class encapsulates a Command object representing a chat bot command.
 */
public abstract class Command {
    /**
     * The arguments of a command, if applicable
     */
    String parameters;
    /**
     * Default constructor for commands with no parameters.
     */
    public Command() {
    }

    /**
     * Constructor for commands that take in parameters.
     *
     * @param parameters Parameters of the command.
     */
    public Command(String parameters) {
        this.parameters = parameters;
    }

    /**
     * Returns parameters of the command.
     * @return String of parameters.
     */
    public String getParameters() {
        return this.parameters;
    }

    /**
     * Returns true if this command is a ByeCommand
     *
     * @return boolean isExit.
     */
    public boolean isExit() {
        return this instanceof ByeCommand;
    }

    /**
     * Carries out execution of given command.
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     * @throws PaggroException
     */
    public abstract void execute(Lister lister, Ui ui, Storage storage) throws PaggroException;
}
