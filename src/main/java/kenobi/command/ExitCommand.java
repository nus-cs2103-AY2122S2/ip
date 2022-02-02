package kenobi.command;

/**
 * The ExitCommand class encapsulates the command to exit the Kenobi program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the termination of the Kenobi program.
     *
     * @return a feedback from the execution of the command.
     */
    @Override
    public String execute() {
        return "Goodbye, old friend";
    }
}
