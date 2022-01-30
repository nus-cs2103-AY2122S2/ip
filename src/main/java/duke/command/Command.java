package duke.command;
import duke.DukeException;
import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
* Abstract class to represent the framework of a Command.  
*/
public abstract class Command {

    protected CommandType commandType;
    protected boolean active;

    /**
     * Initializes the Command object. 
     * @param commandType Enum representing the Command being executed by the user. 
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
        this.active = true;
    }

    /** 
    * Returns a boolean reflecting if the current chat session with Duke is active or not.
    * @return boolean Boolean indicatinf if the chat session is active.
    */
    public abstract boolean isActive();

    /**
     * A method that performs the logic associated with user-request command, using 
     * TaskList, Ui & Storage. 
     * @param taskList The TaskList containing current tasks. 
     * @param ui The Ui object interacting with the user.
     * @param storage The Storage object for loading & saving of user's tasks.
    */ 
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    
}
