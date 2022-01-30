package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Duke chatbot object.
 * Entry point of the chatbot.
 * Initialises the application and starts the interaction with the user.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Creates a new Duke chatbot
     */
    public Duke() {
        try {
            ui = new Ui();
            storage = new Storage("/ip/data");
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.replyUser(e.getMessage());
        }
    }

    /**
     * Starts up the program.
     *
     * @param args arguments supplied by the user at program launch
     */
    public static void main(String... args) {
        new Duke().run();
    }

    private void run() {
        ui.greetUser();
        boolean isExit = false;

        do {
            try {
                String request = ui.readCommand();
                Command c = new Parser().parseCommand(request);
                String result = c.execute(tasks, ui, storage);
                ui.replyUser(result);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.replyUser(e.getMessage());
            }
        } while (!isExit);
    }
}


