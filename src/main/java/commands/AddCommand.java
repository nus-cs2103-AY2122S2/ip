package commands;

import tasks.*;
import ui.Ui;
import storage.Storage;

public class AddCommand extends Command {
	private String input;
	private String type;
	private TaskList tasks;
	private Task added;


	public AddCommand(String commandType, String details) {
		this.type = commandType;
		this.input = details;
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
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		this.tasks = tasks;
		String[] processedInput;
		switch (type) {
		case "todo":
			this.added = new ToDo(input);
			break;
		case "event":
			processedInput = input.split("/at", 2);
			this.added = new Event(processedInput[0], processedInput[1]);
			break;
		case "deadline":
			processedInput = input.split("/by", 2);
			this.added = new Deadline(processedInput[0], processedInput[1]);
			break;
		}
		this.tasks.add(added);

		ui.printFormatted(new String[]{
				"Got it. I've added this task:",
				"  " + added,
				"Now you have " + tasks.size() + " tasks in the list"});
	}
}
