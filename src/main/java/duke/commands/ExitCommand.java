package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a command to cause the application to quit.
 */
public class ExitCommand extends Command {
    public ExitCommand(){
        super();
    }


    @Override
    public boolean isExit(){
        return true;
    }


    /**
     * Executes and shows a goodbye message to the Ui.
     *
     * @param storage Not used.
     * @param ui The Ui to display the goodbye message to.
     * @param taskManager Not used.
     * @return true after displaying goodbye message to the ui.
     * @see ExitCommand#execute(Ui)
     */
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager){
        return execute(ui);
    }

    /**
     * Executes and shows a goodbye message to the Ui.
     *
     * @param ui
     * @return true after displaying goodbye message to the ui.
     */
    public boolean execute(Ui ui){
        ui.showBye();
        return true;
    }
}
