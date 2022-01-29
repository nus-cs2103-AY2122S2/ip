package duke;
import java.io.IOException;
import java.text.ParseException;

public class Duke {

    private final Ui ui;
    private final Parser parser;
    private final Storage storage;
    private final TaskList tasks;

    private Duke() throws IOException, ParseException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        tasks = new TaskList(storage.read());
    }

    private void run() {

        ui.showWelcome();
        boolean isQuit = false;

        while (!isQuit) {
            try {

                String input = ui.getCommand();
                String[] cmd = parser.splitCmd(input);

                if (parser.checkQuit(cmd[0].trim())) {
                    isQuit = true;
                } else {
                    String task = cmd.length < 2 ? "" : cmd[1];
                    parser.runCmd(cmd[0].trim(), task.trim(), tasks, ui, storage);
                }

            } catch (DukeException | IOException e) {
                ui.showError(e.toString());
            }
        }

        ui.showGoodBye();
    }

    public static void main(String[] args) throws IOException, ParseException {
        new Duke().run();
    }
}