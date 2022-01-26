package duke.commands;

import duke.tasks.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;

/**
 * Represents a list command recognized by the parser.
 * Upon execution, it will print out all existing tasks in the
 * task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand object.
     *
     * @param taskList a container of existing tasks in the program.
     * @param io a manager that deals with interactions with the user, used to
     *           print the information of the tasks.
     * @param storage a manager that deals with storing and loading of files.
     */
    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) {
        io.showMessage("Here are the tasks in your list:");
        int count = 0;
        for (int i = 0; i < taskList.getSize(); i++) {
            io.showMessage(i + 1 + ". " + taskList.get(i).toString());
        }
    }
}
