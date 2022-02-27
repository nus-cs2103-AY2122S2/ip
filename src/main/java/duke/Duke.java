package duke;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.util.Constants;

/**
 * Main logic of the bot.
 */
public class Duke {
    private final Ui ui;
    private TasksList taskslist;
    private Storage storage;
    private Parser parser;

    /**
     * Constructs Duke object.
     * Initializes the UI, TasksList, Storage, and Parser for use.
     */
    public Duke() {
        this.ui = new Ui();
        this.taskslist = new TasksList();
        this.storage = new Storage();
        this.parser = new Parser();

        try {
            taskslist.importStorageStrings(storage.importData());
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
    }

    /**
     * Runs the main logic of Duke.
     * Stops after user input == "bye".
     */
    public void run() {
        this.ui.greetUser();

        Scanner sc = new Scanner(System.in);

        try {
            String response = taskslist.importStorageStrings(storage.importData());
            ui.print(response);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }

        while (true) {
            String instruction = sc.nextLine();
            String log = parser.parse(instruction, taskslist, storage);
            ui.print(log);
            if (log.equals(Constants.BYE)) {
                return;
            }
        }
    }

    public String getResponse(String input) {
        String output = parser.parse(input, taskslist, storage);
        return output;
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
