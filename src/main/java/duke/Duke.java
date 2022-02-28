package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * The type Duke.
 */
public class Duke {

    private final Storage storage;
    private final List tasks;
    private final Ui ui;

    /**
     * Constructs a chatterbot.
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
            Command command = Parser.parseUserInput(input);
            if (command != null) {
                try {
                    tempResult = command.execute(tasks, ui, storage);
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
