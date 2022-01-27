package Duke;

public abstract class Command {
	public abstract void execute(TaskList tasks, Ui ui, Save save);
}
