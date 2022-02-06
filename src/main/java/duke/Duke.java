package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Duke extends Application {

    public static final String FILE_PATH = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private static Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.loadData());
    }

    public void run() {
        storage.readFile();
        ui.printWelcomeMessage();
        runTillTerminate();
        ui.printExitMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public Ui getUi(){
        return ui;
    }

    @Override
    public void start (Stage stage) {
        try {
            ui.buildStage(stage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            storage.saveData(tasks);
            Platform.exit();
        }
        stage.show();
    }

    private void runTillTerminate() {
        Parser.CommandType commandType;
        boolean isExit = false;
        do {
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            commandType = Parser.getCommandType(userInput);

            try {
                Command c = Parser.processUserInput(commandType, userInput, tasks);
                Parser.processUserInput(commandType, userInput, tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printInvalidCommand();
            }
        } while (!isExit);
    }

    public String getResponse(String userInput) {
        try {
            Parser.CommandType commandType = Parser.getCommandType(userInput);
            Command c = Parser.processUserInput(commandType, userInput, tasks);

            if (c instanceof ByeCommand) {
                Platform.exit();
                storage.saveData(tasks);
                return ((ByeCommand) c).execute(tasks, ui);
            }
            return c.execute(tasks, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
