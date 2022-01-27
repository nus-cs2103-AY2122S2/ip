package Duke.command;

import Duke.Save;
import Duke.TaskList;
import Duke.Ui;

public abstract class Command {
	public abstract void execute(TaskList tasks, Ui ui, Save save);
}
