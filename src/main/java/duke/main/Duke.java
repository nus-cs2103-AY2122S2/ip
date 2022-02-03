package duke.main;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private static final String FILE_PATH = "data/tasks.txt";
    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;

    /**
     * Constructs a Duke program that stores task data at the specified file path.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
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
