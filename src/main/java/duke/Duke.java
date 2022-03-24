package duke;

import java.io.IOException;

public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        taskList = storage.load();
        ui = new Ui();
    }

    public void run() throws IOException {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(storage, taskList, ui);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data.txt").run();
    }
}
