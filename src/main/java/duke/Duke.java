package duke;

import duke.commands.Command;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {
    private static final Ui ui = new Ui();
    private static final TaskList tasks = new TaskList();
    private static final Storage storage = new Storage("../data");

    public String getResponse(String input) {
        String output = "";
        Command c= ui.read(input);
        output = c.execute(tasks, ui, storage);
        return output;
    }

}

