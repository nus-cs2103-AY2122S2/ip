package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.util.Constants;

public class Duke {
    private final Ui ui;
    private TasksList taskslist;
    private Storage storage;
    private Parser parser;

    public Duke() {
        this.ui = new Ui();
        this.taskslist = new TasksList();
        this.storage = new Storage();
        this.parser = new Parser();
    }

    public void run() {
        this.ui.initialGreet();

        Scanner sc = new Scanner(System.in);

        try {
            String response = taskslist.importStorageStrings(storage.importData());
            ui.print(response);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }

        while (true) {
                String instruction = sc.nextLine();
                String log = parser.parse(instruction, taskslist, storage);
                ui.print(log);
                if (log == "BYE") {
                    return;
                }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
