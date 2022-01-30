package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Storage;

/** 
* Represents a Duke chatbot. 
* A Duke chatbot will save or load the user's tasks in a local file. 
*/

public class Duke {

    TaskList taskList;
    String userName;
    String taskFilePath;
    Ui ui;
    Storage storage;
    
    /**
     * The main method which intializes Duke & runs it. 
     * @param args Unused. 
     * return Nothing. 
    */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /** 
     * Constructor to initialize Duke & its various components. 
     * @param filePath Path of savefile of tasks information 
     * @return Nothing. 
    */
    public Duke(String filePath) {
        taskFilePath = filePath;
        ui = new Ui();
        storage = new Storage(taskFilePath, ui);
        // load TaskList from existing data 
        taskList = new TaskList(storage.loadFileContents());
    }

    /** 
     * Runs the main logic of Duke. 
     * @param Nothing. 
     * @return Nothing. 
    */
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
