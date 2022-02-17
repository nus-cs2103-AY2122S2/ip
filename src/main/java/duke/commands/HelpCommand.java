package duke.commands;

import duke.tasks.TaskList;

/**
 * Represent a help command that upon execution, displays all possible
 * commands and their format to the user.
 */
public class HelpCommand extends Command {

    protected static final String COMMAND_INFO = "Here are all my commands:\n"
            + "1. help - displays all commands\n"
            + "2. list - displays all the created tasks\n"
            + "3. todo - adds a todo task\n"
            + "4. deadline - adds a deadline task\n"
            + "5. event - adds an event task\n"
            + "6. delete - deletes an existing task\n"
            + "7. mark - marks an existing task as completed\n"
            + "8. unmark - marks an existing task as not completed\n"
            + "9. find - displays all tasks that matches a provided substring\n"
            + "10. sort - sorts all tasks according to a function\n"
            + "11. bye - exits the program";

    /**
     * Constructor of a help command.
     */
    public HelpCommand() {
        isModify = false;
        isExit = false;
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
