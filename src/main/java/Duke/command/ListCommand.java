package Duke.command;

import Duke.Save;
import Duke.task.Task;
import Duke.TaskList;
import Duke.Ui;

public class ListCommand extends Command {
	@Override
	public void execute(TaskList tasks, Ui ui, Save save) {
		System.out.println("\t____________________________________________________________");
		System.out.println("\t Here are the tasks in your list:");
		for (int taskCount = 0; taskCount < tasks.getCount(); taskCount++) {
			Task mainTask = tasks.getTask(taskCount);
			System.out.println("\t " + (taskCount + 1) + "." + mainTask.track()
					+ mainTask.getStatus() + " " + mainTask);
		}
		System.out.println("\t____________________________________________________________");
	}
}
