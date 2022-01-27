package commands;

import exceptions.OutOfRangeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Class which handles un-marking of tasks in list as not done
 */
public class UnMarkCommand extends Command {
	private TaskList tasks;
	private int pos;

	public UnMarkCommand(int pos) {
		this.pos = pos - 1;
	}

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
	public boolean ends() {
		return false;
	}

	/**
	 * Method to execute the UnMark command
	 * Marks the task stored at index as not done
	 * @param tasks tasks list to be modified
	 * @param ui to help with printing of messages
	 * @param storage To deal with saving of tasklist
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException {
		this.tasks = tasks;
		if (pos >= tasks.size()) {
			throw new OutOfRangeException();
		}
		Task unmarked = tasks.get(pos);
		unmarked.unmark();
		tasks.set(pos, unmarked);

		ui.printFormatted(new String[]{"I've marked this task as not done yet:", "  " + tasks.get(pos)});
	}
}
