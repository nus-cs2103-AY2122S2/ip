package com.duke;

import com.duke.modules.Parser;
import com.duke.modules.Storage;
import com.duke.modules.TaskList;
import com.duke.modules.Ui;

public class Duke {
    private Parser cmdParser;
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        storage = Storage.getInstance();
        taskList = new TaskList(storage);
        ui = new Ui();
    }

    /**
     * Gets a string response to the user's input.
     * @param input User input.
     * @return A response string.
     */
    public String getResponse(String input) {
        return ui.initialize(input, taskList);
    }
}
