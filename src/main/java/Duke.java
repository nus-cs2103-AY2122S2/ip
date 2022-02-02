import duke.command.ClearTaskCommand;
import duke.command.Command;
import duke.command.SetupCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This class is the main controller of the application.
 */
public class Duke {
    /**
     * Coordinates the interactions between various other classes to accomplish the logic
     * of the application.
     * 
     * @param args not used.
     */
    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        
        try {
            new SetupCommand().execute(ui, taskList, storage);
        } catch (DukeException exception) {
            ui.showErrorMessage(exception.getMessage());
            new ClearTaskCommand().execute(ui, taskList, storage);
        }
        
        boolean isQuitting = false;
        while (!isQuitting) {
            try {
                String cmd = ui.getCommand();
                Command command = Parser.parse(cmd);
                command.execute(ui, taskList, storage);
                isQuitting = command.isBye();
            } catch (DukeException exception) {
                ui.showErrorMessage(exception.getMessage());
            }
        }
    }
}