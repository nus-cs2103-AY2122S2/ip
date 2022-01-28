package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Class which handles listing of tasks in list
 */
public class ListCommand extends Command {
	private TaskList tasks;

	public ListCommand() {}

	/**
	 * Method to get the modified tasklist after command execution
	 * @return TaskList
	 */
	@Override
	public TaskList getList() {
		return tasks;
	}

	/**
	 * Method to see if command ends the main program loop
	 * @return true if it ends main program
	 */
	@Override
	public boolean endsProgram() {
		return false;
	}

	/**
	 * Method to execute the list command
	 * Lists out all tasks stored in the tasklist
	 * @param tasks tasks list to be modified
	 * @param ui to help with printing of messages
	 * @param storage To deal with saving of tasklist
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		this.tasks = tasks;

		System.out.println(INDENT + "Here are the tasks in your list:");
		for (int i = 0; i < tasks.size(); i++) {
			System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
		}
	}
}
