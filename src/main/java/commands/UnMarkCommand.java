package commands;

import tasks.Task;

import java.util.ArrayList;

public class UnMarkCommand extends Command {
	private ArrayList<Task> tasklist;
	private int pos;

	public UnMarkCommand(ArrayList<Task> tasklist, int pos) {
		this.tasklist = tasklist;
		this.pos = pos - 1;
		Task unmarked = tasklist.get(this.pos);
		unmarked.unmark();
		tasklist.set(this.pos, unmarked);
	}

	@Override
	public ArrayList<Task> getList() {
		return tasklist;
	}

	@Override
	public boolean ends() {
		return false;
	}

	@Override
	public void execute() {
		printFormatted(new String[]{"I've marked this task as not done yet:", "  " + tasklist.get(this.pos)});
	}
}
