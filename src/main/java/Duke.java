public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.taskList = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            ui.showError(e);
            this.taskList = new TaskList();
        }
    }

    public void run() {
        this.ui.showIntro();
        boolean hasExited = false;

        do {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                hasExited = !command.execute(this.taskList, this.ui, this.storage);
            } catch (DukeException e) {
                ui.showError(e);
            }
        } while (!hasExited);

        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Duke("./data/saved.txt").run();
    }
}
