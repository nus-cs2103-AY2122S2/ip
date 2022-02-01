package kenobi.command;

/**
 * The InvalidCommand class encapsulates the commands unknown to the Kenobi program.
 */
public class InvalidCommand extends Command {
    /**
     * Returns the feedback of an invalid command.
     *
     * @return a feedback from the execution of the command.
     */
    @Override
    public String execute() {
        return "It's not in the archives! Your command is invalid";
    }
}
