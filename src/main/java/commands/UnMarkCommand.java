package commands;

import exceptions.OutOfRangeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class UnMarkCommand extends Command {
	private TaskList tasks;
	private int pos;

	public UnMarkCommand(int pos) {
		this.pos = pos - 1;
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
		if (pos >= tasks.size()) {
			throw new OutOfRangeException();
		}
		Task unmarked = tasks.get(this.pos);
		unmarked.unmark();
		tasks.set(this.pos, unmarked);

		ui.printFormatted(new String[]{"I've marked this task as not done yet:", "  " + tasks.get(this.pos)});
	}
}
