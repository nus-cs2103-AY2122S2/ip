import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import command.Commands;
import ui.Ui;
import parser.Parser;
import tasklist.TaskList;
import storage.Storage;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    private static final String hyphenate = "    ____________________________________________________________";

    public Duke(String databasePath) {
        ui = new Ui();
        storage = new Storage(databasePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException err) {
            ui.showsLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcomeUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                System.out.println("");
                // System.out.println("1");
                String fullCommand = ui.readCommand();
                // System.out.println("2");
                ui.showLine(); // show the divider line ("_______")
                // System.out.println("3");
                Commands c = Parser.parse(fullCommand);
                // System.out.println("4");
                c.execute(tasks, ui, storage);
                // System.out.println("5");
                isExit = c.isExit();
                // System.out.println("6");
            } catch (NoSuchElementException err) {
                ui.showError("Run Error " + err.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.closePrinter();
    }

    public static void main(String[] args) {
        new Duke("./DukeDatabase.txt").run();
    }
}
