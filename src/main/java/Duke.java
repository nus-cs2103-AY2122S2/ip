import Task.TaskList;
import Duke.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) throws Exception {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.read());
    }

    public void run() throws Exception {
        ui.intro(); // start
        Parser p = new Parser();
        p.process(tasks);
        storage.write(tasks.getList());
        ui.terminate(); // end
    }

    public static void main(String[] args) throws Exception {
        new Duke("data/duke.txt").run();
    }
}
