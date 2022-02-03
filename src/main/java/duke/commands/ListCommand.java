package duke.commands;

import java.util.ArrayList;

import duke.managers.Storage;
import duke.managers.Ui;
import duke.tasks.TaskList;

/**
 * Represents a list command recognized by the parser.
 * Upon execution, it will print out all existing tasks in the
 * task list.
 */
public class ListCommand extends Command {

    /**
     * This command does not need any additional user input.
     *
     * @param tokens a String array that represents the user input.
     */
    @Override
    public void handleParam(String[] tokens) { }

    /**
     * Returns a boolean that specifies whether the user input matches the Command.
     *
     * @return a boolean that indicates whether this object is the correct Command.
     */
    @Override
    public boolean checkIdentifier(String input) {
        return input.equals("list");
    }

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
        ArrayList<String> taskSet = taskList.list();
        io.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < taskSet.size(); i++) {
            io.showMessage(i + 1 + ". " + taskSet.get(i));
        }
    }
}
