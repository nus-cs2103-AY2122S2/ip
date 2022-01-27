package Duke.command;

import Duke.Save;
import Duke.TaskList;
import Duke.Ui;

public class UnmarkCommand extends Command {
	int taskNum;

	public UnmarkCommand(int taskNum) {
		this.taskNum = taskNum;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Save save) {
		try {
			tasks.getTask(taskNum).unmark();
			System.out.println("\t____________________________________________________________");
			System.out.println("\t OK, I've marked this task as not done yet:");
			System.out.println("\t\t" + tasks.getTask(taskNum).track()
					+ tasks.getTask(taskNum).getStatus() + " " + tasks.getTask(taskNum));
			System.out.println("\t____________________________________________________________");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("\t____________________________________________________________");
			System.out.println("\t☹ Woof Woof!!! This task cannot be found with my Wonka eyes!!!");
			System.out.println("\t____________________________________________________________");
		}
	}
}
