package Duke;

public class MarkCommand extends Command {
	int taskNum;

	MarkCommand(int taskNum) {
		this.taskNum = taskNum;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Save save) {
		try {
			tasks.getTask(taskNum).mark();
			System.out.println("\t____________________________________________________________");
			System.out.println("\t Nice! I've marked this task as done:");
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
