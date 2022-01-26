package commands;

import tasks.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;

public class ByeCommand extends Command {
	private TaskList tasks;

	@Override
	public TaskList getList() {
		return tasks;
	}

	@Override
	public boolean ends() {
		return true;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		this.tasks = tasks;
		try{
			storage.saveFile(tasks);
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
		ui.printFormatted(new String[]{"Bye. Hope to see you again soon!"});
	}
}
