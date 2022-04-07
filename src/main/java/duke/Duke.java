package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;


public class Duke {

    private static Ui ui;
    private TaskList taskDataList;

    /** Constructor of Duke Object*/
    public Duke() {
        ArrayList<Task> inputDatabase = (ArrayList<Task>) Storage.readFile();
        taskDataList = new TaskList(inputDatabase);
    }

    /**
     * Initialises and starts the chatBot Duke.
     * @param args The command line arguments.
     * @throws DukeException Throws DukeException when input causes exception to the chatBot.
     */
    public static void main(String[] args) throws DukeException {
        Duke chatBot = new Duke();
        chatBot.run();
    }

    private void run() {
        ui = new Ui();
        ui.hello();
        Scanner chatInput = new Scanner(System.in);
        String input = chatInput.nextLine();
        while (!input.equals("bye")) {
            try {
                Parser inputParser = new Parser(input);
                Command currCommand = inputParser.parse();
                currCommand.callCommand(this.taskDataList);
            } catch (DukeException e) {
                ui.showError(e);
            }
            input = chatInput.nextLine();
        }
        Storage.saveToFile(this.taskDataList.getTaskStore());
        ui.bye();
        chatInput.close();
    }

    public String getResponse(String input) {
        try {
            Parser inputParser = new Parser(input);
            Command currCommand = inputParser.parse();
            return currCommand.callCommand(this.taskDataList);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
