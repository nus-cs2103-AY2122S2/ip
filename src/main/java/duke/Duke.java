package duke;

import duke.helpers.Parser;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;

import java.io.IOException;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String dirPath, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath, filePath);
        try {
            this.tasks = new TaskList(this.storage.loadFileContents());
        } catch (IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        String dirPath = "./src/main/data";
        String filePath = "./src/main/data/duke.txt";
        String userInput = "";

        while (!userInput.equals("bye")) {
            userInput = ui.readCommand();
            Parser.parse(userInput);
        }
        ui.closeScanner();

    }

    public static void main(String[] args) throws IOException {
        String dirPath = "./src/main/data";
        String filePath = "./src/main/data/duke.txt";
        Duke duke = new Duke(dirPath, filePath);
        duke.run();
    }
}