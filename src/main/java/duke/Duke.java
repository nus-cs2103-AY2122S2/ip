package duke;

import duke.parser.Parser;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.command.Command;

import java.io.IOException;
import java.io.File;

public class Duke {

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("./data/duke.txt");
    }

    public void run() throws IOException, DukeException {

        File dir = new File("./data"); 
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File("./data/duke.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        Duke duke = new Duke();

        ui.showWelcome();
        tasks = new TaskList(storage.retrieveData());
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readFullLine();
            Command c = parser.parse(input);
            c.execute(storage, ui, tasks);
            isExit = c.isExit();
        }
        ui.showClosing();

    }

    /**
     * Runs the Duke software.
     */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke().run();
    }
}