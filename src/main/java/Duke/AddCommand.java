package Duke;

public class AddCommand extends Command {
	Task task;

	AddCommand(Task task) {
		this.task = task;
	}

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
