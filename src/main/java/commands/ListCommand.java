package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
	private TaskList tasks;

	public ListCommand() {}

	@Override
	public TaskList getList() {
		return tasks;
	}

	@Override
	public boolean ends() {
		return false;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		this.tasks = tasks;

		System.out.println(INDENT + "Here are the tasks in your list:");
		for (int i = 0; i < tasks.size(); i++) {
			System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
		}
	}
}
