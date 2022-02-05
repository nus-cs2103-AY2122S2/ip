package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * The type Duke.
 */
public class Duke {

    private Storage storage;
    private List tasks;
    private Ui ui;

    /**
     * Create a chatterbot.
     * @param filePath The file's storage path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new List(storage.load());
    }

    public String getResponse(String input) {
        String tempResult = "";
        try {
            Command c = Parser.parseUserInput(input);
            if (c != null) {
                try {
                    tempResult = c.execute(tasks, ui, storage);
                } catch (IOException e) {
                    tempResult = e.getMessage();
                }
            }
        } catch (DukeException e) {
            tempResult = e.getMessage();
        }
        return tempResult;
    }
}
