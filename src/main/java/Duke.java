import java.lang.System;
import java.io.IOException;



public class Duke {

    private final Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     *
     * @param path
     */
    public Duke(String path) {
        ui = new Ui();
        try {
            storage = new Storage(path);
            tasks = new TaskList(storage.readTasks());
        } catch (IOException ie) {
            ui.printStorageIoError();
            System.exit(0);
        } catch (DukeException de) {
            ui.printDukeException(de);
            tasks = new TaskList();
        }

    }


    public void run() {
        ui.printIntroduction();
        boolean isExit = false;

        while (!isExit) {
            try {
                String stringCommand = ui.readCommand();
                Command command = Parser.parseCommand(stringCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException de) {

                de.getMessage();

            } catch (IOException ie) {
                ui.printUpdateIoError(ie);
            }
        }

        ui.closeScanner();
        ui.printExitMessage();
    }

    public String getResponse(String input) {
        try {
            //String stringCommand = ui.readCommand();
            Command command = Parser.parseCommand(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException de) {
            ui.printDukeException(de);
        } catch (IOException ie) {
            ui.printUpdateIoError(ie);
        }
        return "Unknown Command?";
    }


}