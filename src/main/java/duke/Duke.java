package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Duke is an interactive bot that helps the user to note down important tasks.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskMaster taskMaster;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskMaster = new TaskMaster(storage.loadTasks());
    }

//    /**
//     * Kickstarts the Duke bot to begin taking in and reacting to user input.
//     */
//    public void run() {
//        ui.greet();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String[] userCommand = ui.readCommand();
//                Command command = Parser.parse(userCommand[0], userCommand[1]);
//                command.execute(this.taskMaster, this.ui, this.storage);
//                isExit = command.isExit();
//            } catch (DukeException e) {
//                System.out.println(e);
//            }
//        }
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command command = Parser.parse(input);
            response = command.execute(this.taskMaster, this.ui, this.storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

}