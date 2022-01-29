import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList task;
    private Ui ui;
    public static int COUNTER = 1;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            task = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            task = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        DukeManager dukeManager = new DukeManager();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String inputCommand = ui.readCommand();
                String testCommand = dukeManager.test(inputCommand);
                Command<String> newTask = Parser.parseCommand(testCommand, task, storage, ui);
                isRunning = newTask.isRunning();
            } catch (DukeException e){
                System.err.print(e + "\n");
            }
        }
        ui.showBye();
    }

    public static void main(String[] args) {
        String pathToFile = "./data/duke.txt";
        new Duke(pathToFile).run();
    }
}

