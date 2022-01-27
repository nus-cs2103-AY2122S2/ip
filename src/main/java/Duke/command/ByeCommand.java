package Duke.command;

import Duke.Save;
import Duke.TaskList;
import Duke.Ui;

public class ByeCommand extends Command {
	@Override
	public void execute(TaskList tasks, Ui ui, Save save) {
		ui.showExitMessage();
	}
}
