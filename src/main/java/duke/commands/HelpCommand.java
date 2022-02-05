package duke.commands;

import duke.tasks.TaskList;

/**
 * Represent a help command that upon execution, displays all possible
 * commands and their format to the user.
 */
public class HelpCommand extends Command {

    protected static final String COMMAND_INFO = "Here are all my commands:\n"
            + "1. help (displays all commands)" + "\n"
            + "2. list (displays all the created tasks)" + "\n"
            + "3. todo <task information>" + "\n"
            + "4. deadline <task information> /by <date yyyy-mm-dd> <time hh:mm>" + "\n"
            + "5. event <task information> /at <date yyyy-mm-dd> <start time hh:mm>-<end time hh:mm>" + "\n"
            + "6. delete <valid task number> (deletes the task with the given task number)" + "\n"
            + "7. mark <valid task number> (marks the task with the given task number as done)" + "\n"
            + "8. mark <valid task number> (unmarks the task with the given task number as undone)" + "\n"
            + "9. find <search info> (displays all tasks with info similar to the provided details)" + "\n"
            + "10. bye (exits the program)";

    /**
     * Constructor of a help command.
     */
    public HelpCommand() {
        modifyData = false;
        exitProgram = false;
    }

    /**
     * Not used in this command as user input is irrelevant.
     *
     * @param tokens a String array that represents the user input, not used in this command.
     */
    @Override
    public void handleParam(String[] tokens) {}

    /**
     * Returns a boolean that specifies whether the user input matches the Command.
     *
     * @return a boolean that indicates whether this object is the correct Command.
     */
    @Override
    public boolean checkIdentifier(String input) {
        return input.equals("help");
    }

    /**
     * Executes the HelpCommand object and returns a String that contains
     * information on all commands in the system and their format.
     *
     * @param taskList a container of existing tasks in the program, never used
     *                 in this command.
     * @return a String that displays all commands in the program.
     */
    @Override
    public String execute(TaskList taskList) {
        return COMMAND_INFO;
    }
}
