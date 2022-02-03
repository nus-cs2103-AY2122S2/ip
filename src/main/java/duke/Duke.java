package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;
import java.io.IOException;

public class Duke {

    private static final String LOG_PATH = "data/log.txt";
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        Ui.welcome();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.readTasks());
        } catch (DukeException e) {
            Ui.printBorder();
            Ui.print(e.getMessage());
            Ui.printBorder();
            taskList = new TaskList();
        }
    }

    public void run() {
        //add scanner onwards
        Scanner input = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = input.nextLine();
                Ui.printBorder();
                isExit = Parser.parse(command, taskList);

                // Updates log file
                try {
                    storage.updateTasks(taskList);
                } catch (IOException e) {
                    System.out.printf(e.getMessage());
                }
            } catch (DukeException e) {
                Ui.print(e.getMessage());
            } finally {
                Ui.printBorder();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(LOG_PATH).run();
    }
}
