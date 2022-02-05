package duke;

import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {

    public static final String FILE_PATH = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadData());
    }

    private void run() {
        storage.readFile();
        ui.printWelcomeMessage();
        runTillTerminate();
        ui.printExitMessage();
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

    private void runTillTerminate() {
        Parser.CommandType commandType;
        do {
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            commandType = Parser.getCommandType(userInput);

            try {
                Parser.processUserInput(commandType, userInput, tasks);
                storage.saveData(tasks);
            } catch (DukeException e) {
                ui.printInvalidCommand();
            }
        } while (commandType != Parser.CommandType.BYE);
    }
}
