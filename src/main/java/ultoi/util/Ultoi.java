package ultoi.util;

import java.nio.file.Path;

import ultoi.command.Command;

/**
 * Represents a bot named Ultoi that could help users memorize their tasks.
 *
 * @author: snoidetx
 * @version 0.0.1
 */
public class Ultoi {
    private Storage storage;
    private TaskList tasks;
    private UltoiUi ui;

    /**
     * Creates a new ultoi.util.Ultoi chatbot.
     *
     * @param filePath Path to the file to load and save tasks.
     */
    public Ultoi(Path filePath) {
        this.ui = new UltoiUi();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (UltoiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns the welcome message of Ultoi.
     *
     * @return Welcome message of Ultoi.
     */
    public String showWelcomeMsg() {
        return ui.showWelcomeMsg();
    }

    public String getResponse(String input) {

        assert input != null : "User input is null";

        try {
            Command cmd = Parser.parse(input);
            return cmd.execute(this.ui, this.tasks, this.storage);
        } catch (UltoiException e) {
            return ui.showError(e);
        }
    }
}
