package karen;

import karen.command.Command;

public class Karen {
    public Karen() {}

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
