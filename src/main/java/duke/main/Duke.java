package duke.main;

import duke.command.Command;
import duke.exception.DukeException;
import duke.utility.UI;
import duke.task.TaskList;
import duke.utility.Parser;
import duke.utility.Storage;


/**
 * Duke is a Todo list command line application that allows you to create, delete, mark, and save tasks
 */

public class Duke {
    /**
     * To load and save tasks into the specified file path
     */
    private Storage storage;
    /**
     * To maintain the current list of tasks
     */
    private TaskList tasks;
    /**
     * To handle any input output interaction with users
     */
    private UI ui;

    /**
     * Returns a Duke application that loads previously saved tasks.
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



    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.tasks, this.ui, this.storage);
        } catch (Exception e) {
            return e.toString();
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