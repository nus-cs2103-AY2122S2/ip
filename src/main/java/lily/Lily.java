package lily;

import lily.task.LilyException;

/**
 * Main class which runs an interactive CLI-based chatbot which manages your todos
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Lily {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Lily(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui = new Ui(true);
        } catch (LilyException e) {
            tasks = new TaskList();
            ui = new Ui(false);
        }
        parser = new Parser(tasks, ui, storage);
    }

    public void run() {
        ui.showWelcome(tasks);
        this.parser.parse(); // executes commands also
    }

    public static void main(String[] args) {
        new Lily("data/tasks.txt").run();
    }
}