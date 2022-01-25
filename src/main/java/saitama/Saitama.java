package saitama;

import saitama.commands.Command;
import saitama.exceptions.SaitamaException;

public class Saitama {
    private static String filePath = "data/Saitama.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a chatbot that can keep track of your tasks.
     *
     * @param filePath The file path of the stored task list.
     */
    public Saitama(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * Run the chatbot.
     */
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
            } catch (SaitamaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Saitama(filePath).run();
    }
}

