package commands;

import exceptions.OutOfRangeException;
import storage.Storage;
import tasks.*;
import ui.Ui;

public class DeleteCommand extends Command {
	private TaskList tasks;
	private int deleteIndex;
	private Task deleted;

	public DeleteCommand(int deleteIndex) {
		this.deleteIndex = deleteIndex - 1;

	}

	@Override
	public TaskList getList() {
		return tasks;
	}

	@Override
	public boolean ends() {
		return false;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException {
		this.tasks = tasks;
		if (deleteIndex >= tasks.size()) {
			throw new OutOfRangeException();
		}
		this.deleted = tasks.get(this.deleteIndex);
		this.tasks.remove(this.deleteIndex);

		ui.printFormatted(new String[]{
				"Noted. I've removed this task:",
				"  " + deleted,
				"Now you have " + this.tasks.size() + " tasks in the list"});
	}
}
