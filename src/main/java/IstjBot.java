import java.io.IOException;

public class IstjBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public IstjBot(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());

        } catch (IOException | BotException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new IstjBot("data/tasks.txt").run();
    }
}