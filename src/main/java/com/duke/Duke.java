package com.duke;

import com.duke.command.Command;
import com.duke.exception.DukeException;
import com.duke.task.TaskList;
import com.duke.util.Parser;
import com.duke.util.Storage;
import com.duke.util.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String path) {
        ui = new Ui();
        storage = new Storage(path);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
