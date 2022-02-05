package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
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
     * Constructor of a find command. Specifies that a find command
     * does not require any storage of data/ending the program.
     */
    public FindCommand() {
        modifyData = false;
        exitProgram = false;
    }

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
     * @return a String that displays all the task information in the task list
     *         matching the search string.
     */
    @Override
    public String execute(TaskList taskList) {
        String output = "Here are the matching tasks in your list:" + "\n";
        ArrayList<String> tasksString = taskList.search(searchString);
        for (int i = 0; i < tasksString.size(); i++) {
            output += i + 1 + ". " + tasksString.get(i) + "\n    ";
        }
        return output.trim();
    }
}
