package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a Duke chatbot.
 */
public class Duke {

    protected Storage storage;
    protected Ui ui;
    protected TaskList taskList;

    /**
     * Constructor for a Duke chatbot.
     */
    public Duke() {
        this.storage = new Storage("data/duke.txt");
        this.ui = new Ui();
        this.taskList = this.storage.readFromFile();
    }

//    /**
//     * Initiates the chatbot.
//     *
//     * @throws IOException if there is an error with the input/output.
//     */
//    public void run() throws IOException {
//        this.ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                this.ui.showLine();
//                this.ui.showLeadingSymbol();
//                String fullCommand = this.ui.readCommand();
//                Command command = Parser.parse(fullCommand, this.taskList);
//                isExit = command.execute(this.taskList, this.ui, this.storage);
//            } catch (DukeException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }

    public String getResponse(String fullCommand) throws DukeException {
        String response = "";
        try {
            Command command = Parser.parse(fullCommand, this.taskList);
            response = command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return response;
    }

//    public static void main(String[] args) throws IOException {
//        new Duke().run();
//    }
}
