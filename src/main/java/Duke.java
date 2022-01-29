import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.setUpData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        ui = new Ui(tasks);
        parser = new Parser(tasks);
    }

    public void run() {
        try {
            ui.startConversation(this.parser, this.storage);
        } catch (DukeException e) {
            ui.showIllegalTextError();
        }
    }

    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}
