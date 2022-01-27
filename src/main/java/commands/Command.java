package commands;

import exceptions.DukeException;
import ui.Ui;
import storage.Storage;
import tasks.TaskList;

/**
 * Abstract class from which all commands inherit from
 */
public abstract class Command {
	public final static String INDENT = "     ";

	/**
	 * Abstract method for the execution of command
	 * @param tasks tasks list to be modified
	 * @param ui to help with printing of messages
	 * @param storage To deal with saving of tasklist
	 */
	public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

	/**
	 * Abstract method to see if command ends the main program loop
	 * @return true if it ends main program
	 */
	public abstract boolean ends();

	/**
	 * Abstract method to get the modified tasklist after command execution
	 * @return TaskList
	 */
	public abstract TaskList getList();
}
