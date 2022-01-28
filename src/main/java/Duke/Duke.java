package Duke;

import Duke.command.ByeCommand;
import Duke.command.Command;

/**
 * Represents a chatbot named Duke.
 */
public class Duke {
	public TaskList taskList;
	public Save save;
	public Ui ui;

	/**
	 * Constructor for Duke chatbot which initialises with a saved history Save, list of tasks TaskList, system UI Ui.
	 */
	public Duke() {
		this.save = new Save();
		this.taskList = save.taskList();
		this.ui = new Ui();
	}

	/**
	 * Runs the Duke chatbot.
	 */
	public void run() {
		this.ui.welcome();
		boolean isRunning = true;

		while (isRunning) {
			String command = this.ui.readCommand();
			try {
				Command c = Parser.parse(command);
				c.execute(this.taskList, this.ui, this.save);
				if (c instanceof ByeCommand) {
					isRunning = false;
				}
			} catch (NullPointerException e) {
				ui.showLine();
				ui.tryAgain();
				ui.showLine();
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
