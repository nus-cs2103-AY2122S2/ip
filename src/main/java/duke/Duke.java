package duke;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class Duke {

    Ui ui;
    TaskList taskList;
    Storage storage;
    //21/07/2011 10:48 AM

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



    static void runHelpCommand() {
        System.out.println("    Hello. You can run a few commands with this machine.");
        System.out.println("    1. Type todo to create a task at hand. (eg. todo homework today)");
        System.out.println("    2. Type event to create an event. (eg. event Career Fair /at 26/01/2022 10:00 AM)");
        System.out.println("    3. Type deadline to create an deadline. (eg. deadline CS2103 Assignement /by 29/01/2022 11:59 PM)");
        System.out.println("    4. Type list to see what are the tasks on hand.");

    }
}
