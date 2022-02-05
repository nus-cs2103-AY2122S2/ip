package duke;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import duke.command.Commands;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.storage.Storage;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

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
        //new Duke("./src/main/java/duke/data/DukeDatabase.txt").run();
        new Duke("C:/Users/benny/Desktop/Y2S2/CS2103T_Software_Engineer/Individual_Project/src/main/java/duke/data/DukeDatabase.txt").run();
    }
}
