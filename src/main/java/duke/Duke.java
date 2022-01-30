package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.data.TaskList;
import duke.data.exception.ResourceNotFoundException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {
    private static boolean running;

    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    /**
     * Sets up the required objects and loads up data from the storage file.
     */
    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.running = true;
        try {
            this.storage = new Storage();
            this.taskList = new TaskList(storage.load());
        } catch (ResourceNotFoundException | IOException e) {
            ui.send(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts the program
     */
    public void run() {
        ui.greet();
        commandLoop();
        exit();
    }

    /**
     * Takes input from user until exit command is issued
     */
    public void commandLoop() {
        while (true) {
            try {
                String input = ui.getNextLine();
                Command command = parser.parse(input);
                command.setData(taskList, this, storage);
                String response = command.execute();
                if (response.equals("EXIT")) {
                    break;
                }
                ui.send(response);
            } catch (Exception exception) {
                ui.send(exception.getMessage());
            }
        }
    }

    private void exit() {
        ui.exit();
        System.exit(0);
    }

    /**
     * The main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
