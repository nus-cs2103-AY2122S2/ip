package duke;

import duke.parser.Parser;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

/**
 * Represents a Duke task tracker app.
 */
public class Duke {

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a new Duke object.
     */
    public Duke() throws FileNotFoundException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("./data/duke.txt");
        tasks = new TaskList(storage.retrieveData());
    }

    /**
     * Runs the Duke app.
     *
     * @throws IOException If an I/O error occurs.
     * @throws DukeException If an error related to Duke app occurs.
     */
    public void run() throws IOException, DukeException {

        File dir = new File("./data"); 
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File("./data/duke.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        ui.showWelcome();
        tasks = new TaskList(storage.retrieveData());
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readFullLine();
            Command c = parser.parse(input);
            String res = c.execute(storage, ui, tasks);
            isExit = c.isExit();
        }
        ui.showClosing();
    }

    public String getResponse(String input) throws DukeException, IOException {
        Command c = parser.parse(input);
        String res = c.execute(storage, ui, tasks);
        return res;
    }

    /**
     * The main method that will run the Duke app.
     */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke().run();
    }
}