package dazz;

import dazz.command.Command;
import dazz.exception.DazzException;

/**
 * Represents a chatbot that allows user to keep track of their tasks (i.e a to-do list).
 */
public class Dazz {
    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Creates a Dazz (chat box).
     */
    public Dazz() {
        this.storage = new Storage();
        this.taskList = new TaskList(storage.loadList());
        this.ui = new Ui();
    }

    /**
     * Runs the chatbot, allowing it to receive user inputs
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
            } catch (DazzException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command responseCommand = Parser.parse(input);
            String response = responseCommand.execute(taskList, ui, storage);
            if (responseCommand.isExit()) {
                response = response + "\n Click on 'X' to close the window";
            }
            return response;
        } catch (DazzException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Dazz().run();
    }
}
