package duke;

import duke.command.Command;

/**
 * An instance of Duke, which helps control Storage, TaskList, and UI, managing the application.
 */
public class Duke {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

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
        Command cmd = Parser.parseCommand(input);
        String response = cmd.execute(tasks, ui, storage);
        Parser.setIsExit(cmd.isExit());
        return response;
    }
}
