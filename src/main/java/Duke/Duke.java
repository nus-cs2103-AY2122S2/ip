package Duke;

import Duke.command.ByeCommand;
import Duke.command.Command;

public class Duke {
	public TaskList taskList;
	public Save save;
	public Ui ui;

	public Duke() {
		this.save = new Save();
		this.taskList = new TaskList(save.savedTasks());
		this.ui = new Ui();
	}

	public void run() {
		this.ui.welcome();
		boolean isRunning = true;

		while (isRunning) {
			String command = this.ui.readCommand();
			Command c = Parser.parse(command);
			c.execute(this.taskList, this.ui, this.save);
			if (c instanceof ByeCommand) {
				isRunning = false;
			}
		}
		this.save.save();
		ui.close();
	}


	public static void main(String[] args) {
		Duke duke = new Duke();
		duke.run();
	}
}
