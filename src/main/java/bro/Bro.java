package bro;

import bro.commands.Command;
import bro.exceptions.BroException;

public class Bro {
    private TaskManager manager;
    private Ui ui;
    private Storage storage;
    private boolean hasExited = false;

    public Bro(String filePath) {
        try {
            storage = new Storage(filePath);
            ui = new Ui();
            ui.showLoadFilePath(storage.getFullPath());
            manager = storage.loadTaskManagerFromFile();
            ui.showLoadingComplete();
        } catch (BroException e) {
            ui.showFileReadError();
            ui.showInitializeDefaults();
            manager = new TaskManager();
        }
    }

    public void run() {
        ui.showBanner();
        ui.showList(manager);

        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.getUserInputLine();

                Command command = Parser.parse(userInput);

                System.out.println(command.execute(storage, ui, manager));

                isExit = command.isExit();
            } catch (BroException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            assert !this.hasExited : "Program has already exited!";
            Command command = Parser.parse(input);
            assert command != null : "Command is null!";

            this.hasExited = command.isExit();
            command.execute(storage, ui, manager);
            return command.getResponse();
        } catch (BroException e) {
            return e.getMessage();
        }
    }

    public boolean hasExited() {
        return this.hasExited;
    }
    public static void main(String[] args) {
        new Bro("bro/data").run();
    }
}
