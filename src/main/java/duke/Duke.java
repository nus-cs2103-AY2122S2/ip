package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;


/**
 * duke.Main class which runs the program
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Creates a duke.Duke object with tasks loaded
     *
     * @param filePath path to data stored
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    /**
     * Returns welcome message
     *
     * @return welcome message
     */
    public String getWelcome() {
        return ui.welcomeMessage();
    }

    /**
     * Returns response from user input
     * executes user input as a command
     *
     * @param input for desired command
     * @return response from running command
     */
    public String getResponse(String input) {
        Command cmd;
        String output = " ";
        try {
            cmd = Parser.processInput(input);
            output = cmd.execute(tasks, ui, storage);
            tasks = cmd.getList();
        } catch (DukeException e) {
            output = e.getMessage();
        } finally {
            return output;
        }
    }
}
