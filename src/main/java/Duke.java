import DukeComponent.Storage;
import DukeComponent.TaskList;
import DukeComponent.Ui;
import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.run(sc, tasks, storage);
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
