package Duke.command;

import Duke.Save;
import Duke.task.Task;
import Duke.TaskList;
import Duke.Ui;

/**
 * This AddCommand class will add a task when executed.
 */
public class AddCommand extends Command {
	Task task;

	/**
	 * Constructor for AddCommand which adds the provided task.
	 *
	 * @param task Task to be added.
	 */
	public AddCommand(Task task) {
		this.task = task;
	}

	/**
	 * Executes command by adding task into TaskList.
	 *
	 * @param tasks TaskList of tasks.
	 * @param ui    Ui provided.
	 * @param save  Saved history.
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Save save) {
		tasks.add(task);
		ui.showLine();
		System.out.println("\t Got it. I've added this task:");
		System.out.println("\t\t " + task.track() + task.getStatus() + " " + task.getName());
		System.out.println("\t Now you have " + (tasks.getCount()) + " tasks in the list.");
		ui.showLine();
	}
}
