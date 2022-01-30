package duke;

import java.util.Scanner;

import duke.command.Parser;
import duke.io.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main Class for Duke.
 */
public class Duke {
    /**
     * Main method for Duke.
     *
     * @param args The arguments to run Duke with.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Scanner in = new Scanner(System.in);
        Parser parser = new Parser();
        TaskList taskList = new TaskList();

        Storage.loadFile(System.getProperty("user.dir") + "\\data\\duke.txt", taskList);

        ui.printLogo();
        String userInput = in.nextLine();
        int runResult = parser.run(userInput, ui, taskList);
        while (runResult != -1) {
            if (runResult == 1) {
                Storage.saveFile("data", "duke.txt", taskList.getList());
            }
            userInput = in.nextLine();
            runResult = parser.run(userInput, ui, taskList);
        }
    }

}



