import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException{
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            if (storage.load().equals("")) {
                throw new DukeException("");
            }
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine(); // show the divider line ("_______")
            Command command = Parser.parse(fullCommand, ui);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
            ui.showLine();
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}
