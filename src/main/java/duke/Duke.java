package duke;

import duke.command.Command;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Duke is a Personal Assistant Chatbot that helps a user to keep track of various things.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor class.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/", "duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns the welcome greeting to be shown upon start up.
     *
     * @return Welcome greeting.
     */
    public static String getGreeting() {
        return Ui.showWelcome();
    }

    /**
     * Generates response based on user input.
     *
     * @param input Input from user.
     * @return Response message from Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.showResponse();
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}


