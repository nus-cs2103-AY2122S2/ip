package duke;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Contains main method for duke.Duke chatbot.
 * In charge of initializing UI and storage with taskList.
 */
public class Duke {
    private static final String filePath = "data/duke.txt";

    private final Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Loads the duke.Duke chatbot with a default save file location.
     * If there is no file in the filePath, Storage will create a new file.
     */
    public Duke() {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load(), ui);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            this.tasks = new TaskList(ui);
        }
    }

    /**
     * Loads the duke.Duke chatbot with a given filePath to the saved file.
     * If there is no file in the filePath, Storage will create a new file.
     *
     * @param filePath File's path.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load(), ui);
        } catch (FileNotFoundException e) {
            System.out.println("    File not found!");
            this.tasks = new TaskList(ui);
        }
    }

    public Ui getUi() {
        if (ui != null) {
            return ui;
        } else {
            return new Ui();
        }
    }

    /**
     * Returns a String response from the chatbot to a user input.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            cmd.executeCommand(tasks, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
        return ui.getNextMessage();
    }
}
