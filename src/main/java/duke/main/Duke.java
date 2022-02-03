package duke.main;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;

    /**
     * Constructs a Duke program that stores task data at the specified file path.
     * @param filePath String representing path for storage
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readFullCommand();
                ui.showBorder(false);
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                ui.showResponse();
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showBorder(true);
                if (!isExit) {
                    ui.showQuestionPrompt();
                }
            }
        }
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
