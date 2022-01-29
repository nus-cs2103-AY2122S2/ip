package duke;

import duke.exception.DukeException;
import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.TaskList;
import duke.helper.Ui;

/**
 *
 */
public class Duke {

    //constructor
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Sets up the objects needed for the application to run.
     * Loads the task data stored in the filePath.
     * @param filePath The place where task data are stored.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        taskList = new TaskList(storage.load());
    }

    /**
     * Runs the program till termination.
     */
    public void run(){
        ui.greet();
        String input = null;
        while(true) {
            try {
                input = ui.nextLine().trim();
                if(Parser.isExit(input)) {
                    ui.sayBye();
                    return;
                }
                String message = Parser.parseInputLine(input, taskList);
                ui.print(message);
                storage.save(taskList.getTaskList());

            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Entry point of the application
     * @param args
     */
    public static void main(String[] args){
        new Duke("data/tasks.txt").run();
    }
}
