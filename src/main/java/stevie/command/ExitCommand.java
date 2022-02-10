package stevie.command;

import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;
import stevie.undo.UndoHistory;

/**
 * ExitCommand is used to terminate session with Stevie/
 */
public class ExitCommand extends Command {
    private static final String endMessage = "Bye! Hope to see you again!";
    /**
     * Ui outputs a response string to let user know that session is terminating.
     *
     * @param tasks   task list to make changes on
     * @param storage to handle the saving of data
     * @param ui      to pass a response string for output
     * @return true to terminate the session
     */
    @Override
    public String execute(TaskList tasks, TaskDataHandler storage, StevieUi ui, UndoHistory undoHistory) {
        ui.outputMessage(endMessage);
        return endMessage;
    }

    /**
     * ExitCommand causes program to terminate.
     * @return true as ExitCommand causes program to terminate
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
