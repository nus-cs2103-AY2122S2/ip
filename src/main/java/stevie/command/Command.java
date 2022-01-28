package stevie.command;

import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

/**
 * Command class encapsulates the action of Stevie chatbot executing an instruction given by the user.
 * To create a Command, extend from the Command class and override the execute function.
 */
public abstract class Command {
    /**
     * Executes the instruction given by the user. Execute may change the state of tasks,
     * write to the data storage, and output a response from the ui.
     *
     * @param tasks   task list to make changes on
     * @param storage to handle the saving of data
     * @param ui      to pass a response string for output
     * @return false if Command does not terminate session with Stevie, else true
     */
    public abstract boolean execute(TaskList tasks, TaskDataHandler storage, StevieUi ui);
}
