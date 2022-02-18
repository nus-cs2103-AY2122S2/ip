package bot;

import commands.Command;
import parser.Parser;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a chatbot that listens for user inputs to manipulate their tasks.
 */
public class Bot {
    private final Parser parser;
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Returns a chatbot whose commands, interface, and data operations, are
     * dependent on the arguments supplied.
     *
     * @param parser processes and validates all user inputs.
     * @param ui provides an interface for users to interact with a chat bot.
     * @param taskList provides a set of operations to manipulate user tasks.
     */
    public Bot(Parser parser, Ui ui, TaskList taskList) {
        this.parser = parser;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Begins execution of the chatbot by showing an interface that accepts
     * user inputs, processes them, and reflects the eventual response from
     * the bot.
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                final String query = ui.readCommand();
                final Command command = this.parser.parse(query);
                command.execute(this.ui, this.taskList);
                isExit = command.isExit();
            } catch (Exception ex) {
                ui.showError(ex.getMessage());
            }
        }
    }
}
