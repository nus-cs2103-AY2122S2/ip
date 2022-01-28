import TaskList.TaskList;
import exceptions.DukeException;
import storage.Storage;
import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static final String FILE_PATH = "data/duke.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadData());
    }

    public void run() {
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
                ui.printDivider();
            } catch(DukeException e) {
                ui.printInvalidCommand();
            }
        } while(commandType != Parser.CommandType.BYE);
    }
}
