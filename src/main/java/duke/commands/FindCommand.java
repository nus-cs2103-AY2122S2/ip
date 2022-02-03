package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.managers.Storage;
import duke.managers.Ui;
import duke.tasks.TaskList;


/**
 * Represents a find command recognized by the parser.
 * It stores a String that is to be used for searching. Upon
 * execution of the object, it will attempt to search for tasks in the task list
 * with description matching that of searchString.
 */
public class FindCommand extends Command {

    protected String searchString;

    /**
     * Handles user input and stores the String that is used to search for
     * tasks with matching substrings.
     *
     * @param tokens a String array that represents the user input.
     * @throws DukeException when the user input provided is insufficient to filter the tasks.
     */
    @Override
    public void handleParam(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("Invalid input! Please specify a description for the tasks to search!");
        }
        String searchString = "";
        for (String token : tokens) {
            if (token.equals("find")) {
                continue;
            } else {
                searchString += " " + token;
            }
        }
        this.searchString = searchString.trim();
    }

    /**
     * Returns a boolean that specifies whether the user input matches the Command.
     *
     * @return a boolean that indicates whether this object is the correct Command.
     */
    @Override
    public boolean checkIdentifier(String input) {
        return input.equals("find");
    }

    /**
     * Executes the FindCommand object.
     *
     * @param taskList a container of existing tasks in the program, used to search
     *                 for tasks with matching substrings.
     * @param io a manager that deals with interactions with the user, used to print
     *           notifications to the user.
     * @param storage a manager that deals with storing and loading of files.
     */
    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) {
        io.showMessage("Here are the matching tasks in your list:");
        ArrayList<String> tasksString = taskList.search(searchString);
        for (int i = 0; i < tasksString.size(); i++) {
            io.showMessage(i + 1 + ". " + tasksString.get(i));
        }
    }
}
