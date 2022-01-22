package commands;

import tasks.*;

import java.util.ArrayList;

public class DeleteCommand extends Command {
	private ArrayList<Task> tasklist;
	private int deleteIndex;
	private Task deleted;

	public DeleteCommand(ArrayList<Task> tasklist, int deleteIndex) {
		this.deleteIndex = deleteIndex - 1;
		this.tasklist = tasklist;
		this.deleted = tasklist.get(this.deleteIndex);
		this.tasklist.remove(this.deleteIndex);
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
		printFormatted(new String[]{
				"Noted. I've removed this task:",
				"  " + deleted,
				"Now you have " + this.tasklist.size() + " tasks in the list"});
	}
}
