package main.java.ari;

import main.java.ari.command.ByeCommand;
import main.java.ari.command.Command;
import main.java.ari.parser.Parser;
import main.java.ari.storage.Storage;
import main.java.ari.tasks.TaskList;
import main.java.ari.ui.Ui;

public class Ari {
    private String filePath;

    private Storage store;
    private TaskList toDoList;
    private Ui ui;

    public Ari(String path) {
        filePath = path;
        ui = new Ui();
    }

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
