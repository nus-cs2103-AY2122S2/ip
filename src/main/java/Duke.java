import Duke.Exception.DukeException;
import Duke.Processing.Storage;
import Duke.Processing.Parser;
import Duke.Processing.TaskList;
import Duke.UI.Ui;
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.tasks = new TaskList();
        try {
            storage.load(tasks);
        } catch (DukeException e) {
            ui.errorMessage(e);
        }
    }

    public void run() {
        ui.startmessage();
        String task = ui.read();
        while(!task.equalsIgnoreCase("bye")) {
            ui.divider();
            try {
                Parser.use(task, this.tasks);
            } catch (DukeException e) {
                ui.errorMessage(e);
            }
            task = ui.read();
        }
        try {
            this.storage.write(tasks);
        } catch (DukeException e) {
            ui.errorMessage(e);
        }
        ui.endmessage();
    }
    public static void main(String[] args) {
        new Duke("Previously.txt").run();
    }
        
}
