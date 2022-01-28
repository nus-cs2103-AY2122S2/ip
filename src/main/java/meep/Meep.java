package meep;

import java.io.FileNotFoundException;
import java.io.IOException;

import meep.commands.Command;
import meep.commands.ExitCommand;
import meep.exception.InvalidInputException;
import meep.parser.Parser;
import meep.storage.Storage;
import meep.task.ListTask;
import meep.ui.Ui;




/**
 * Entry point of the Meep application.
 * Initializes the application and starts the interaction with the user.
 */
public class Meep {

    private Storage storage;
    private Parser parser;
    private Ui ui;
    private ListTask taskList;

    public static void main(String[] args) {
        new Meep().run();
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        start();
        readAndExecuteCommand();
        saveTaskToFile();
        System.exit(0);
    }


    /**
     * Sets up all objects needed, loads up the data from the data file and prints the logo.
     */
    private void start() {
        try {
            this.ui = new Ui();
            this.parser = new Parser();
            this.taskList = new ListTask();
            this.storage = new Storage("/java/data.txt", taskList);
            ui.printLogo();
        } catch (IOException | InvalidInputException e) {
            ui.printMsg(e.getMessage());
        }
    }

    /**
     * Reads user input, parse it to command and execute it until exit command.
     */
    private void readAndExecuteCommand() {
        boolean isExit = false;
        String userInput = "In";
        while (!isExit) {
            try {
                userInput = ui.getUserCommand();
                Command command = parser.parseUserInput(userInput, taskList.getList());
                String result = command.execute(taskList);
                ui.printMsg(result);
                isExit = ExitCommand.isExit(command);
            } catch (InvalidInputException e) {
                ui.printMsg(e.getMessage());
                continue;
            }
        }
    }

    /**
     * Saves task to storage file.
     */
    private void saveTaskToFile() {
        try {
            storage.saveTaskToFile(taskList.getList());
        } catch (FileNotFoundException e) {
            ui.printMsg(e.getMessage());
        }
    }


}
