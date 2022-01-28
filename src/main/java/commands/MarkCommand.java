package commands;

import exceptions.OutOfRangeException;
import storage.Storage;
import tasks.*;
import ui.Ui;

/**
 * Class which handles marking of tasks in list as done
 */
public class MarkCommand extends Command {
	private TaskList tasks;
	private int pos;

	public MarkCommand(int pos) {
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
	public boolean endsProgram() {
		return false;
	}

	/**
	 * Method to execute the Mark command
	 * Marks the task stored at index as done
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
		Task marked = tasks.get(pos);
		marked.mark();
		this.tasks.set(pos, marked);

		ui.printFormatted(new String[]{"Nice! I've marked this task as done:", "  " + tasks.get(pos)});
	}
}
