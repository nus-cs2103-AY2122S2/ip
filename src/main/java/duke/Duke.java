package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the Duke Application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class Duke {

    private static TaskList taskList;
    private static Storage storage;
    private static final Parser parser = new Parser();
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Initialization of Duke application.
     */
    private Duke() {
        storage = new Storage(parser);
        try {
            taskList = storage.importTasks();
        } catch (IOException e) {
            Ui.print(Ui.MSG_FILEREADERROR);
        }
    }

    /**
     * Running instance of Duke application.
     */
    private void run() {
        Ui.printWelcome();
        boolean status = true;
        String inputTxt;
        while (status) {
            try {
                inputTxt = sc.nextLine();
                Command c = parser.parse(inputTxt);
                c.execute(taskList, storage);
                status = !c.isExit();
            } catch (DukeException e) {
                Ui.print(e.getMessage());
            } catch (IOException e) {
                Ui.print(Ui.MSG_FILEWRITEERROR);
            }
        }
        Ui.printExit();
    }

    /**
     * Initiation of the Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
