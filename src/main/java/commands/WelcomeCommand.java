package commands;

import tasks.Task;

import java.util.ArrayList;

public class WelcomeCommand extends Command {

	@Override
	public ArrayList<Task> getList() {
		return new ArrayList<Task>();
	}

	@Override
	public boolean ends() {
		return false;
	}

	@Override
	public void execute() {
		printFormatted(new String[]{"Hello! I'm Duke", "What can I do for you?"});
	}
}
