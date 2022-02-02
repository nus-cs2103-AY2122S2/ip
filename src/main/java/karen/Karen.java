package karen;

import karen.command.Command;

/**
 * App interface for managing between UI, Parser, Storage.
 */
public class Karen {
    private Ui ui;
    private Parser parser;
    private Storage storage;

    public Karen() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(this.ui);
    }

    /**
     * Access point for Karen application to manage the start, parsing and
     * termination of application.
     */
    public void start() {
        boolean isExit = false;

        ui.showWelcome();
        while (!isExit) {
            try {
                String fullInput = ui.readInput();
                Command cmd = parser.parseInput(fullInput);
                cmd.execute(ui, storage);
                isExit = cmd.isExit();
            } catch (KarenException err) {
                ui.displayUserInput(err.message);
            }
        }
    }

    public String getResponse(String fullInput) {
        String response;
        try {
            Command cmd = this.parser.parseInput(fullInput);
            response = cmd.execute(this.ui, this.storage);
        } catch (KarenException err) {
            response = err.toString();
        }
        return response;
    }

}
