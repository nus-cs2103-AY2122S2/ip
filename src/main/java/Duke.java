import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RonException e) {
            tasks = new TaskList(filePath);
        }
    }

    public void run() {
        this.ui.start(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
