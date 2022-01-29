package Duke.command;

import Duke.util.Save;
import Duke.util.TaskList;
import Duke.util.Ui;

/**
 * This UnmarkCommand class will mark a task as undone when executed.
 */
public class UnmarkCommand extends Command {
	int taskNum;

	/**
	 * Constructor for UnmarkCommand with a given task number to be marked as undone in the list.
	 *
	 * @param taskNum 0-based index task number to be marked.
	 */
	public UnmarkCommand(int taskNum) {
		this.taskNum = taskNum;
	}

	/**
	 * Executes command by marking task as undone.
	 *
	 * @param tasks TaskList of tasks.
	 * @param ui    Ui provided.
	 * @param save  Saved history.
	 */
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
