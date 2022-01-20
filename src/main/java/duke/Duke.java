package duke;

public class Duke {
    private TaskManager taskManager;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        taskManager = new TaskManager();
        try {
            storage = new Storage(filePath);
            taskManager = new TaskManager(storage.load());
        } catch (DukeException e) {
            ui.print(e.toString());
            taskManager = new TaskManager();
        }
    }

    public void run() {
        ui.greet();
        while (true) {
            String userInput = ui.readCommand();
            String response = Parser.parseCommand(userInput, taskManager);
            ui.print(response);
            if (Parser.isExit(userInput)) {
                try {
                    storage.save(taskManager.getTasks());
                } catch (DukeInvalidFileSaveException e) {
                    ui.print(e.toString());
                }
                return;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
