package Duke;

public class ByeCommand extends Command {
	@Override
	public void execute(TaskList tasks, Ui ui, Save save) {
		ui.showExitMessage();
	}
}
