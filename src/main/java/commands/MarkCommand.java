package commands;

import exceptions.OutOfRangeException;
import storage.Storage;
import tasks.*;
import ui.Ui;

import java.util.ArrayList;

public class MarkCommand extends Command {
	private TaskList tasks;
	private int pos;

	public MarkCommand(int pos) {
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
		Task marked = tasks.get(this.pos);
		marked.mark();
		this.tasks.set(this.pos, marked);

		ui.printFormatted(new String[]{"Nice! I've marked this task as done:", "  " + tasks.get(this.pos)});
	}
}
