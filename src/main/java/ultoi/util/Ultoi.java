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
    private boolean loadsSuccessfully;

    /**
     * Creates a new ultoi.util.Ultoi chatbot.
     *
     * @param filePath Path to the file to load and save tasks.
     */
    public Ultoi(Path filePath) {
        this.ui = new UltoiUi();
        storage = new Storage(filePath);
        this.loadsSuccessfully = true;

        try {
            tasks = storage.load();
        } catch (UltoiException e) {
            this.loadsSuccessfully = false;
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

    /**
     * Returns the laoding status.
     *
     * @return Loading status.
     */
    public String showLoadingStatus() {
        if (this.loadsSuccessfully) {
            return ui.showLoadingSuccess();
        } else {
            return ui.showLoadingError();
        }
    }

    /**
     * Gets the response from Ultoi based on the user input.
     *
     * @param input User input.
     * @return Response from Ultoi.
     */
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
