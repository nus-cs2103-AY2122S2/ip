package duke;

import command.Command;
import exception.DukeException;
import task.TaskList;
import utility.UI;
import utility.Parser;
import utility.Storage;


/**
 * Main class for Duke
 *
 */
public class Duke {

    public UI ui;
    public TaskList tasks;
    public Storage storage;

    /**
     * constructor for Duke
     * @param filePath
     */
    public Duke(String filePath){
        this.ui = new UI();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printException(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.startMessage();
        boolean bye = false;
        while(!bye){
            try {
                String commandString = ui.readLine();
                ui.showLine();
                Command command = Parser.parse(commandString);
                command.execute(this.tasks, this.ui, this.storage);
                bye = command.isExit();
            } catch (DukeException e) {
                this.ui.printException(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "src/main/storage/save.text";
        new Duke(filePath).run();
    }
}
