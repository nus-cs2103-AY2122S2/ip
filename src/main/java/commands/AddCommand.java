package commands;

import tasks.*;

import java.util.ArrayList;

public class AddCommand extends Command {
	private String input;
	private String type;
	private ArrayList<Task> tasklist;
	private Task added;


	public AddCommand(ArrayList<Task> tasklist, String commandType, String details) {
		this.type = commandType;
		this.input = details;
		this.tasklist = tasklist;
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
		this.tasklist.add(added);
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
				"Got it. I've added this task:",
				"  " + added,
				"Now you have " + tasklist.size() + " tasks in the list"});
	}
}
