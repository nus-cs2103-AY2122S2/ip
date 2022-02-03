package Duke.Main;

import Duke.Commands.Command;
import Duke.Commands.CommandEmpty;
import Duke.DukeException.DukeException;
import Duke.System.Parser;
import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;

/**
 * The Duke program implements an application that
 * manages three different types of tasks based on the
 * user's input
 *
 * @author  Melvin Chan Zijun
 *
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Sole constructor.
     *
     * @param filePath - the file path of the folder where
     *                   the data of duke will be stored
     * @throws DukeException - if folder or text file creation was
     *                         unsuccessful
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * This method is used to run Duke.
     * It first displays a simple greeting,
     * followed by a simple tutorial of the commands
     * It will then prompt the user for their input,
     * then execute the respective command.
     * This process of prompting for input and
     * execution will repeat until the user inputs
     * the exit command.
     */
    public void run() {
        ui.showGreeting();
        ui.showLine();
        ui.showTutorial();
        ui.showLine();
        boolean isExitCommand = false;
        do {
            Command c = new CommandEmpty();
            try {
                String fullCommand = ui.promptCommand();
                c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.showException(e);
            } finally {
                ui.showLine();
            }
            isExitCommand = c.isExit();
        } while (!isExitCommand);
    }

    /**
     * This is the main method of Duke.
     * It is used to start the Duke program.
     *
     * @param args - not used
     */
    public static void main(String[] args) {
        new Duke("data").run();
    }
}
