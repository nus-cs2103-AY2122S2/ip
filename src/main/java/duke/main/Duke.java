package duke.main;

import java.io.IOException;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exceptions.DukeException;
import duke.operations.Parser;
import duke.operations.Storage;
import duke.operations.TaskList;
import duke.operations.Ui;

/**
 * Represents a Duke bot to record tasks.
 *
 * @author Zachary Chan
 * @version 0.1
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean hasExit = false;

    /**
     * Initializes a <code>Duke</code> object using a Duke constructor.
     */
    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }

    /**
     * Initializes the Storage Class.
     *
     * @throws IOException if file/directory not found.
     */
    public void initializeStorage() throws IOException {
        storage = new Storage("/DukeSaveDirectory/DukeSaveFile.txt", System.getProperty("user.home"));
        storage.load();
    }

    /**
     * Generates a response to user input.
     *
     * @param input takes in the user input.
     * @return a String to be printed in the GUI.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c instanceof ExitCommand) {
                hasExit = true;
            }
            String output = c.execute(tasks);
            Storage.updateTextFile();
            return output;
        } catch (DukeException e) {
            return ui.showDukeError(e.getMessage());
        }
    }

    public boolean getHasExit() {
        return hasExit;
    }
}
