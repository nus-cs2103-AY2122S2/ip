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
     * @param filePath relative path of a text file to load past task.
     */
    public Dazz(String filePath) {
        this.storage = new Storage(filePath);
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

    public static void main(String[] args) {
        new Dazz("data/tasks.txt").run();
    }
}
