import java.io.IOException;
import java.nio.file.Paths;

import paggro.storage.Storage;
import paggro.ui.Ui;
import paggro.lister.Lister;
import paggro.exception.PaggroException;
import paggro.command.Command;
import paggro.parser.Parser;

public class PaggroBot {
    Storage storage;
    Ui ui;
    Lister lister;

    public  PaggroBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            lister = storage.loadTasks();
        } catch (PaggroException e) {
            ui.showError(e.getMessage());
            lister = new Lister();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Command cmd = Parser.parse(input);
                cmd.execute(lister, ui, storage);
                isExit = cmd.isExit();
            } catch (PaggroException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new PaggroBot("./data/paggro.txt").run();
    }
}
