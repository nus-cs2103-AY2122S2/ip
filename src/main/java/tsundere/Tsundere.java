package tsundere;

import command.Command;
import task.TaskList;

/**
 * A chatbot that manages tasks and stores them in data/tasks.txt
 */
public class Tsundere {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A chatbot that manages tasks and stores them in data/tasks.txt
     *
     * @param filePath string of the file path for the save file
     */
    public Tsundere(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TsundereException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * runs the chatbot
     */
    public void run() {
        ui.showIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TsundereException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * creates a new Tsundere class and runs it
     *
     * @param args unused param
     */
    public static void main(String[] args) {
        new Tsundere("data/tasks.txt").run();
    }

}
