package duke.main;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TransferQueue;

import duke.command.Command;
import duke.dukeexceptions.DeadlineException;
import duke.dukeexceptions.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;

public class Duke {
    /**
     * A task taking chatbot.
     *
     */
    private final Storage storage;
    private Ui ui;
    private TaskList taskList;


    /**
     * Constructor for Duke.
     * @param filePath the data file
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("No path exist");
            taskList = new TaskList();
        }
    }

    public String getResponse(String input) throws DukeException, IOException {
        String response = Parser.parseCommand(input, this.taskList, this.ui, this.storage);
        return response;
    }
}
