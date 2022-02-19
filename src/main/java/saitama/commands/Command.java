package saitama.commands;

import saitama.exceptions.SaitamaException;
import saitama.storage.Storage;
import saitama.tasks.TaskList;
import saitama.ui.Ui;


/**
 * An abstract class of commands for Saitama.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList The TaskList associated with the chat bot
     * @param ui The Ui associated with the chat bot
     * @param storage The storage associated with the chat bot
     * @return Saitama's reply to the executed command
     * @throws SaitamaException if the command is invalid
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws SaitamaException;

    /**
     * A boolean representing whether this command is the ExitCommand.
     *
     * @return true if it is the ExitCommand, false otherwise.
     */
    public abstract boolean isExit();
}
