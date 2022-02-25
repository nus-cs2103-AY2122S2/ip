import java.io.IOException;

public class Duke {
    private Ui ui;
    private TaskList<Task> tasks;
    private Storage storage;

    public Duke() throws IOException {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = this.storage.load();
    }

    public void run() {
        ui.showWelcome();
        boolean exitDuke = false;

        while (!exitDuke) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                exitDuke = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
