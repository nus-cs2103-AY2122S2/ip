package Duke.command;

import Duke.Save;
import Duke.task.Task;
import Duke.TaskList;
import Duke.Ui;

public class DeleteCommand extends Command {
	int taskNum;

	public DeleteCommand(int taskNum) {
		this.taskNum = taskNum;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Save save) {
		Task deleteTask = tasks.getTask(this.taskNum);
		tasks.delete(this.taskNum);
		System.out.println("\t____________________________________________________________");
		System.out.println("\t Noted. I've removed this task:");
		System.out.println("\t\t " + deleteTask.track() + deleteTask.getStatus() + " "
				+ deleteTask);
		System.out.println("\t Now you have " + tasks.getCount() + " tasks in the list.");
		System.out.println("\t____________________________________________________________");
	}
}
