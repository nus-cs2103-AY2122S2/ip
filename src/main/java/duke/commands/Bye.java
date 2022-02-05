package duke.commands;

import duke.Storage;
import duke.TextUi;
import tasks.TaskList;

/**
 * Represents a command that allows users to exit the duke chat bot
 */
public class Bye extends Command {
    /**
     * Method that executes the return of a "bye" string
     * @param taskList a taskList containing all existing tasks
     * @param ui a ui object
     * @param storage a storage object that is able to read and write to storage file
     * @return an exit message
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        return "bye";
    }
}
