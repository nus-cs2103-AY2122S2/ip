import commands.Command;
import commands.ExitCommand;
import exception.InvalidInputException;
import parser.Parser;
import storage.Storage;
import task.ListTask;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Entry point of the Meep application.
 * Initializes the application and starts the interaction with the user.
 */
public class Meep {

    public static void main(String[] args) {
        Storage storage = new Storage();
        Parser parser = new Parser();
        Ui ui = new Ui();
        ui.printLogo();
        ListTask taskList = new ListTask();

        try {
            taskList.addTaskList(storage.readTaskFile(storage.getPath()));
        } catch (IOException | InvalidInputException e) {
            ui.print(e.getMessage());
            // exit if no data file
            return;
        }


        boolean isExit = false;
        String userInput = "In";
        while (!isExit) {
            try {
                userInput = ui.getUserCommand();
                Command command = parser.parseUserInput(userInput, taskList.getList());
                String result = command.execute(taskList);
                ui.print(result);
                isExit = ExitCommand.isExit(command);
            } catch (InvalidInputException e) {
                ui.print(e.getMessage());
                continue;
            }
        }

        try {
            storage.saveTasktoFile(taskList.getList());
        } catch (FileNotFoundException e) {
            ui.print(e.getMessage());
        }

    }
}