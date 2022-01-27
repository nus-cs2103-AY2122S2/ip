package dazz;

import dazz.command.Command;
import dazz.exception.DazzException;

public class Dazz {
    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;

    public Dazz(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadList());
        this.ui = new Ui();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DazzException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Dazz("data/tasks.txt").run();
    }
}

