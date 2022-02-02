package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static TaskList taskList;
    private static Storage storage;

    private Duke() {
        storage = new Storage();
        try {
            taskList = storage.importTasks();
        } catch (IOException e) {
            Ui.print(Ui.MSG_FILEREADERROR);
        }
    }

    public void run() {
        final Parser parser = new Parser(storage);
        final Scanner sc = new Scanner(System.in);
        Ui.printWelcome();
        boolean status = true;
        String inputTxt;
        while (status && sc.hasNext()) {
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

    public static void main(String[] args) {
        new Duke().run();
    }
}
