package duke.commands;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @return response from the exit command.
     */
    public String execute() {
        String exitResponse = "EXIT";
        return exitResponse;
    }
}
