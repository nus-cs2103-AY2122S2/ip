package karen;

import karen.command.Command;

/**
 * App interface for managing between UI, Parser, Storage.
 */
public class Karen {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        Storage storage = new Storage(ui);
        boolean isExit = false;

        ui.showWelcome();
        while (!isExit) {
            try {
                String fullInput = ui.readInput();
                Command cmd = parser.parseInput(fullInput);
                cmd.execute(ui, storage);
                isExit = cmd.isExit();
            } catch (KarenException err) {
                ui.displayUserInput(err.msg);
            }
        }

        // clean up tasks
        if (isExit) {
            storage.saveTasks();
        }

    }
}
