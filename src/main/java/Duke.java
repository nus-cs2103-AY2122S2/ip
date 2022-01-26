
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {

    private static TaskList taskList;
    private static Storage storage;
    private static final Ui ui = new Ui();

    private Duke() {
        storage = new Storage();
        try {
            taskList = storage.importTasks();
        } catch (IOException e) {
            ui.print(ui.MSG_FILEREADERROR);
        }
    }

    public void run() {
        final Parser parser = new Parser(ui, storage);
        final Scanner sc = new Scanner(System.in);
        ui.printWelcome();
        boolean status = true;
        String inputTxt;
        while (status && sc.hasNext()) {
            try {
                inputTxt = sc.nextLine();
                Command c = parser.parse(inputTxt);
                c.execute(taskList, ui, storage);
                status = c.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            } catch (IOException e) {
                ui.printFileWriteError();
            }
        }
        ui.printExit();
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
