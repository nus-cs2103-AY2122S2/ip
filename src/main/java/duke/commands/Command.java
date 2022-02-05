package duke.commands;

import duke.Storage;
import duke.TextUi;
import tasks.TaskList;

/**
 * Class that handles the different types of commands from the user
 */
public class Command {
    /**
     * Method that is called to execute a command
     * @param taskList a taskList containing all existing tasks
     * @param ui a ui object
     * @param storage a storage object that is able to read and write to storage file
     * @return an empty string
     */
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        return "";
    }
}
