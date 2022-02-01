package com.duke;

import com.duke.modules.Parser;
import com.duke.modules.Storage;
import com.duke.modules.TaskList;
import com.duke.modules.Ui;

public class Duke {
    private Parser cmdParser;

    public Duke() {
    }

    /**
     * Runs the chatbot.
     * @param args
     */
    public static void main(String[] args) {
        Duke lumu = new Duke();

        lumu.botInitialize();
    }

    private void botInitialize() {
        Storage storage = Storage.getInstance();
        TaskList taskList = new TaskList(storage);
        Ui ui = new Ui();
        ui.initialize(taskList);

    }

}
