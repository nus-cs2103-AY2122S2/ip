package commands;

import tasks.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
	private static ArrayList<Task> tasklist;

	public ListCommand(ArrayList<Task> tasklist) {
		this.tasklist = tasklist;
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
		printFormatted(tasklist);
	}

	public static void printFormatted(ArrayList<Task> tasklist) {
		System.out.println(INDENT + "Here are the tasks in your list:");
		for (int i = 0; i < tasklist.size(); i++) {
			System.out.println(INDENT + (i + 1) + "." + tasklist.get(i));
		}
	}
}
