package duke;

import duke.command.Command;
import duke.command.ExitCommand;

/**
 * An instance of Duke, which helps control Storage, TaskList, and UI, managing the application.
 */
public class Duke {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private boolean isExit;

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.read());
        } catch (DukeException e) {
            ui.showError(e.toString());
            tasks = new TaskList();
        }
    }

    /**
     * Gets response.
     *
     * @param input the input
     * @return the response
     */
    public String getResponse(String input) {
        Command cmd = null;
        try {
            cmd = Parser.parseCommand(input);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        // ensures cmd is not null so that it can be executed
        assert cmd != null;
        String response = cmd.execute(tasks, ui, storage);
        isExit = isExitCommand(cmd);
        return response;
    }

    /**
     * Checks whether an exit command has been executed.
     * @return true if an {@code ExitCommand} has been executed, false otherwise
     */
    public boolean getExitCondition() {
        return this.isExit;
    }

    private boolean isExitCommand(Command cmd) {
        return cmd instanceof ExitCommand;
    }
}
