package duke.commands;

import java.util.ArrayList;

import duke.tasks.TaskList;

/**
 * Represents a list command recognized by the parser.
 * Upon execution, it will print out all existing tasks in the
 * task list.
 */
public class ListCommand extends Command {

    /**
     * Constructor of a list command. Specifies that a list command
     * does not require any storage of data/ending the program.
     */
    public ListCommand() {
        modifyData = false;
        exitProgram = false;
    }

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
     * @return a String that displays all the task information in the task list.
     */
    @Override
    public String execute(TaskList taskList) {
        ArrayList<String> taskSet = taskList.list();
        String output = "Here are the tasks in your list:" + "\n    ";
        for (int i = 0; i < taskSet.size(); i++) {
            output += i + 1 + ". " + taskSet.get(i) + "\n    ";
        }
        return output.trim();
    }
}
