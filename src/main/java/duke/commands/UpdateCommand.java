package duke.commands;

/**
 * Represents a command to update a task.
 */
public abstract class UpdateCommand extends Command {

    public UpdateCommand(String userInput) {
        super(userInput);
    }

    /**
     * Factory method to create a more specific command to either update the name or date of the task.
     *
     * @param userInput The string entered by the user.
     * @return a Update command to update the name or date of the command.
     * @see UpdateDateCommand
     * @see UpdateNameCommand
     */
    public static UpdateCommand of(String userInput) {

        if (userInput.contains("/date")) {
            return new UpdateDateCommand(userInput);
        } else if (userInput.contains("/name")) {
            return new UpdateNameCommand(userInput);
        }
        return new InvalidUpdateCommand();
    }
}
