package duke;

import duke.commands.Command;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.nio.file.Paths;

public class Duke {
    private static final Ui ui = new Ui();
    private static final TaskList tasks = new TaskList();
    private final Storage storage = new Storage("./data/record.txt");
    private boolean isExit = false;

    public String getResponse(String input) {
        String output;
        Command c= ui.read(input);
        output = c.execute(tasks, ui, storage);
        isExit = c.isExit();
        return output;
    }

    public boolean isExit() {
        return isExit;
    }


}

