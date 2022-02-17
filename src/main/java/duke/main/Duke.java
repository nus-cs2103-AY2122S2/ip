package duke.main;

import java.io.IOException;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        // in here i will call the scanner fom Ui, and run the inputs to the relevant
        // methods of classes.
        System.out.println(ui.greeting);
        boolean isExit = false;
        // instantiate the Parser class
        Parser parser = new Parser(tasks);
        while (!isExit) {
            try {
                System.out.println(ui.lineBreak);
                // call for the input to be received
                String line = ui.nextInput();
                // parse the input to understand what it says
                // should return the commmand type - bye, find, todo, deadline, event etc.
                // if bye, then update isExit
                String commandType = parser.scanInput(line);
                if (commandType.equals("bye")) {
                    isExit = true;
                } else {
                    // Parser will handle the progress of the code for the other commands.
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public static void main(String[] args) throws IOException {
        /**
         * Insinuates that the run method is called after passing the file path to the
         * Duke constructor.
         * What is run() supposed to do? Which file accepts the command line arguments?
         * I think its Ui, where
         */
        new Duke("data/duke.txt").run();
    }
}