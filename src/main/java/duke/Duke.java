package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.util.Parser;
import duke.util.Save;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a chatbot named Duke.
 */
public class Duke {
    private TaskList taskList;
    private Save save;
    private Ui ui;

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


    /**
     * Main method to run
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
