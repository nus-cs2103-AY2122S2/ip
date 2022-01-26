package funbox;

import java.io.IOException;
import java.util.Scanner;
import funbox.util.Ui;
import funbox.util.Parser;
import funbox.util.Storage;
import funbox.util.TaskList;
import funbox.command.Command;
import funbox.exception.FunBoxExceptions;

/**
 * The FunBox class is used as the outer shell of FunBoxGear,
 * which contains the functionality of FunBox
 */
public class FunBox {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        Parser parser = new Parser();
        Storage storage = new Storage(ui, parser);
        TaskList taskList = storage.getTaskList();
        ui.greetUser();
        boolean isExit = false;
        while(!isExit) {
            try {
                String command = sc.nextLine();
                Command c = parser.parseCommand(command, taskList, ui);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (FunBoxExceptions | IOException e) {
                ui.showError(e.getMessage());
            }

        }

        sc.close();
    }
}
