package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeManager;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Duke class containing entry point to program
 */
public class Duke {

    public static int COUNTER = 1;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    /**
     * Constructor for Duke object
     * @param filePath filepath of data to be saved/retrieved
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Entry point to run Whey, taking in inputs and outputting the appropriate response
     */
    public void run(String input) {
        DukeManager dukeManager = new DukeManager();
            try {
                String testCommand = dukeManager.test(input);
                Command<String> newTask = Parser.parseCommand(testCommand, taskList, storage, ui);
                if (!newTask.isRunning()) {
                    System.exit(0);
                }
            } catch (DukeException e) {
                System.out.print(e.getMessage() + "\n");
            }
        }

    /**
     * Function to convert system out from Whey to String
     * @param input input from text input
     * @return string output from Productivilisaiton
     */
    public String getResponse(String input) {
        ByteArrayOutputStream responseOutput = new ByteArrayOutputStream();
        PrintStream stringConvert = new PrintStream(responseOutput);
        PrintStream old = System.out;
        System.setOut(stringConvert);
        run(input);
        System.out.flush();
        System.setOut(old);
        return responseOutput.toString();
    }
}

