package commands;

import tasks.Task;

import java.util.ArrayList;

public class ByeCommand extends Command {

	@Override
	public ArrayList<Task> getList() {
		return new ArrayList<Task>();
	}

	@Override
	public boolean ends() {
		return true;
	}

	@Override
	public void execute() {
		printFormatted(new String[]{"Bye. Hope to see you again soon!"});
	}
}
