package duke;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class Duke {

    Ui ui;
    TaskList taskList;
    Storage storage;

    public Duke (String filePath) throws IOException, ParseException {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<Task>());
        }

    }

    public void run() throws IOException, DukeException, ParseException {

        Parser parser = new Parser();

        while(true) {
            try {
                Command command = parser.readCommand();
                command.runCommand(taskList, ui, storage);
            } catch (DukeException e) {
                ui.showWrongCommand();
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException, ParseException {
        new Duke("data/tasks.txt").run();
    }


}
