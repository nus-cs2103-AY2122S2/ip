import duke.Parser;
import duke.Storage;
import duke.UI;

import task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) throws Exception {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readData());
    }

    public void run() throws Exception {
        ui.printIntro(); // start
        Parser parser = new Parser();
        parser.processData(tasks);
        storage.writeData(tasks.getList());
        ui.printTerminate(); // end
    }

    public static void main(String[] args) throws Exception {
        new Duke("data/duke.txt").run();
    }
}
