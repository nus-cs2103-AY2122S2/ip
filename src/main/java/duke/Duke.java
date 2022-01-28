package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.task.TaskList;
import duke.task.Storage;
import ui.Ui;


public class Duke {

    TaskList taskList;
    String userName;
    String taskFilePath;
    Ui ui;
    Storage storage;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public Duke(String filePath) {
        taskFilePath = filePath;
        ui = new Ui();
        storage = new Storage(taskFilePath, ui);
        // load TaskList from existing data 
        taskList = new TaskList(storage.loadFileContents());
    }

    public void run() {
        
        // Jarvis introduces himself, asks for user's name & greets user
        userName = ui.showWelcome();

        // initialize the chat session as active 
        boolean active = true;

        while(active) {
            try {
                String userInput = ui.readCommand();
                Command c = Parser.parse(userInput);
                c.execute(taskList, ui, storage);
                active = c.isActive();
            }
            
            catch(DukeException e) {
                ui.showError(e.getMessage());
            }
        }

    }

}
