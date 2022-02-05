package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;



public class Duke{
    private TaskManager manager;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            ui.showLoadFilePath(storage.getFullPath());
            manager = storage.loadTaskManagerFromFile();
            ui.showLoadingComplete();
        } catch (DukeException e) {
            ui.showFileReadError();
            ui.showInitializeDefaults();
            manager = new TaskManager();
        }
    }


    public void run() {
        ui.showBanner();
        ui.showList(manager);

        boolean isExit = false;

        while(!isExit) {
            try {
                String userInput = ui.getUserInputLine();

                Command command = Parser.parse(userInput);
                System.out.println(command.execute(storage,ui,manager));

                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(storage, ui, manager);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke("duke/data").run();
    }
}
