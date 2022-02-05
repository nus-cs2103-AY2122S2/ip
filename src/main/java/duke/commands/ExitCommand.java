package duke.commands;

import duke.tasks.TaskList;

/**
 * Represents an exit command recognized by the parser.
 * Upon execution of the object, it will print a goodbye message and flip the boolean
 * to terminate the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor of an exit command. Specifies that an exit command
     * ends the program.
     */
    public ExitCommand() {
        modifyData = false;
        exitProgram = true;
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
        return input.equals("bye");
    }

    /**
     * Executes the ExitCommand object.
     *
     * @param taskList there is no modification to the task list in this command.
     * @return null as this command does not have any feedback to the user.
     */
    @Override
    public String execute(TaskList taskList) {
        return "Bye. Hope to see you again soon!";
    }
}
