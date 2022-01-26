public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String fileName) {
        try {
            ui = new Ui();
            storage = new Storage(fileName);
            tasks = new TaskList(storage.loadALlTasks());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome(storage.getPath());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
