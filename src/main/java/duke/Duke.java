package duke;

import duke.Commands.DukeCommand;
import duke.Exceptions.DukeException;
import duke.Tasks.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructor for the Duke class
     * @param filePath The path of the file which acts as the databse
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError(e.getMessage());
        }

    }

    public String executeCommand(String input) throws IOException, DukeException {
        try {
            DukeCommand c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch(DukeException e) {
            return e.getMessage();
        }
    }
}
