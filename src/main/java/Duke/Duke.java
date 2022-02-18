package Duke;

import Duke.Exception.DukeException;
import Duke.Processing.Parser;
import Duke.Processing.Storage;
import Duke.Processing.TaskList;
import Duke.UI.Ui;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.tasks = new TaskList();
        try {
            storage.load(tasks);
        } catch (DukeException e) {
            ui.errorMessage(e);
        }
    }
    public Duke() {

    }

    /**
     * Takes in the user input and returns the appropriate response
     *
     * @param input is the user input
     * @return A string output
     */
    public String getResponse(String input) {
        String output;
        try {
            output = Parser.parse(input, this.tasks);
            this.storage.write(this.tasks);
        } catch (DukeException e) {
            output = ui.errorMessage(e);
        }
        return output;
    }

        
}
