import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String currentWorkingDirectory, String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, currentWorkingDirectory);
        try {
            this.tasks = new TaskList();
            storage.load();
        } catch (IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showDukeError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        new Duke(home,"/data/tasks.txt").run();
    }
}
