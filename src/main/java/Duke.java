public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readFullCommand();
                ui.showBorder(false);
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                ui.showResponse();
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showBorder(true);
                if (!isExit) {
                    ui.showQuestionPrompt();
                }
            }
        }
    }
}
