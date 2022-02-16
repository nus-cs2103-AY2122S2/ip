package duke;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.*;
import duke.ui.TextUI;


public class Duke {

    private static TaskList taskList;
    private static Storage storage;
    private static TextUI textUI;
    private static Parser parser;

    private static void initialize() {
        textUI = new TextUI();
        storage = new Storage();
        parser = new Parser();

        try {
            taskList = Storage.loadTasklist();
        } catch (DukeException e) {
            taskList = new TaskList();
            System.out.println(e.getMessage());
        }

        Command.defineTaskList(taskList);
        textUI.printWelcomeMessage();
    }

    private static void run() {
        Command currCommand = null;
        do {
            String userInputCommand = textUI.getUserCommand();
            try {
                currCommand = parser.parseCommands(userInputCommand);
                textUI.printMessage(currCommand.execute());
                storage.saveTasklist(taskList);
            } catch (DukeException e) {
                textUI.printMessage(e.getMessage());
            }
        } while (ExitCommand.isRunning());
    }


    public static void main(String[] args) {
        initialize();
        run();
    }
}
