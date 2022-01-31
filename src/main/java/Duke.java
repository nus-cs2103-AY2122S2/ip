import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui(tasks);
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) { //abstract to DukeException
            ui.showError("Error loading file.");
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while(!isExit) {
            try {
                  String fullCommand = ui.readCommand();
                  ui.divide();    // show the divider line ("__________")
                  Command c = Parser.parse(fullCommand);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.divide();
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
