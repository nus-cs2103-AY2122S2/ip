import batman.parser.Parser;
import batman.storage.Storage;
import batman.tasks.TaskList;
import batman.ui.Ui;

import java.io.IOException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui("Batman");
        storage = new Storage(filePath);
        try {
            taskList = new TaskList();
        }
        catch (Exception e) {
            ui.showLoadingError(e.getMessage());
        }
    }

    private void run() {
        try {
            taskList.getTasksFromFile(storage.load());
            ui.greeting();
            String userInput = ui.read();
            while (!userInput.equals("bye")) {
                ui.printOutput(Parser.parseInput(userInput));
                userInput = ui.read();
                storage.writeToFile(taskList.getTaskList());
            }
            ui.exit();
        }
        catch (IOException e) {
            ui.showLoadingError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke("/data/tasks.txt");
        chatBot.run();
    }
}
