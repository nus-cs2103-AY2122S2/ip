package Duke.command;

import Duke.Save;
import Duke.task.Task;
import Duke.TaskList;
import Duke.Ui;

/**
 * This DeleteCommand class will delete a task provided with a 0-based index when executed.
 */
public class DeleteCommand extends Command {
	int taskNum;

	/**
	 * Constructor for DeleteCommand which provides an index to delete.
	 *
	 * @param taskNum 0-based index.
	 */
	public DeleteCommand(int taskNum) {
		this.taskNum = taskNum;
	}

	/**
	 * Executes command by deleting the given indexed task from TaskList.
	 *
	 * @param tasks TaskList of tasks.
	 * @param ui    Ui provided.
	 * @param save  Saved history.
	 */
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
