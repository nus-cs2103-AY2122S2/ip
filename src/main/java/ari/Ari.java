package ari;

import ari.command.ByeCommand;
import ari.command.Command;
import ari.parser.Parser;
import ari.storage.Storage;
import ari.tasks.TaskList;
import ari.ui.Ui;

/**
 * Represents Ari functions
 */
public class Ari {
    private String filePath;

    private Storage store;
    private TaskList toDoList;
    private Ui ui;

    /**
     * Constructor of Ari
     *
     * @param path path to location of save file
     */
    public Ari(String path) {
        filePath = path;
        ui = new Ui();
    }

    /**
     * Starts the program
     */
    public void run() {
        ui.displayWelcomeMessage();

        Parser par = new Parser();
        store = new Storage();
        store.setFile(filePath);
        toDoList = store.load();

        String input = ui.getUserInput();
        Command cmd = par.parse(input);
        while (!ByeCommand.isGoodBye(cmd)) {
            cmd.setTaskList(toDoList);
            ui.displayMessage(cmd.execute());
            cmd = par.parse(ui.getUserInput());
            store.save(toDoList);
        }
        ui.displayMessage(cmd.execute());

    }
}
