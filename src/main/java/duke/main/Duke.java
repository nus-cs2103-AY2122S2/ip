package duke.main;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.scene.image.Image;

public class Duke {
    private static final String FILE_PATH = "data/tasks.txt";
    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;
    private boolean hasExited;
    private boolean hasLoadingError;

    /**
     * Constructs a Duke program that stores task data at the specified file path.
     */
    public Duke() {
        hasExited = false;
        hasLoadingError = false;
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            hasLoadingError = true;
            tasks = new TaskList();
        }
    }

    public Pair<String, Image> getResponse(String input) {
        if (hasExited) {
            return new Pair<String, Image>("", ui.getResponseImage());
        }
        try {
            Command c = Parser.parseCommand(input);
            c.execute(tasks, ui, storage);
            hasExited = c.isExit();
            return new Pair<String, Image>(ui.getResponse(), ui.getResponseImage());
        } catch (DukeException e) {
            Image errorImage = ui.getErrorImage();
            return new Pair<String, Image>(e.getMessage(), errorImage);
        } finally {
            ui.clear();
        }
    }

    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    public boolean hasExited() {
        return hasExited;
    }

    public boolean hasLoadingError() {
        return hasLoadingError;
    }

    public void setHasLoadingError(boolean hasLoadingError) {
        this.hasLoadingError = hasLoadingError;
    }
}
