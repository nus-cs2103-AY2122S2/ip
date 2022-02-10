package duke;

import duke.command.Command;
import duke.data.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Function to generate a response to user input.
     */
    public String getResponse(String input) {
        String output = "";
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            output = ui.getOutput();
        } catch (DukeException e) {
            e.printStackTrace();
        }
        if (!output.equals("")) {
            return output;
        } else {
            return "Sorry I dont know";
        }
    }
}
