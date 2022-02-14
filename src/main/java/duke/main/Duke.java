package duke.main;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.system.Parser;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

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
     * Overloaded constructor for javaFX integration.
     * Constructor without params required for javaFX to run.
     * Previous file path specification have been transferred here.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns response from executing the command
     *
     * @return String message from execution or error
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showException(e);
        }
    }

    /**
     * Returns greeting message
     *
     * @return String greeting message
     */
    public String getGreeting() {
        return ui.showGreeting();
    }

    /**
     * Returns tutorial message
     *
     * @return String tutorial message
     */
    public String getTutorial() {
        return ui.showTutorial();
    }
}
