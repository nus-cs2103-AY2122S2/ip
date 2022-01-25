import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentMap;

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
        boolean isExit = false;

        ui.greetUser();

        boolean isLoop = true;
        while(!isExit) {
            try {
                String command = sc.nextLine();
                Command c = parser.parseCommand(command, taskList, ui);
                c.execute(taskList, ui, storage);
                isExit = c.isExit;
            } catch (FunBoxExceptions | IOException e) {
                ui.showError(e.getMessage());
            }

        }

        sc.close();
    }
}
