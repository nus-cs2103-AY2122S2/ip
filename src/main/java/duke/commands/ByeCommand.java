package duke.commands;

/**
 * Command class to end the bot from running.
 */
public class ByeCommand extends Command<String> {
    /**
     * Constructor to execute the stopping of this bot.
     */
    public ByeCommand() {
        execute();
    }

    private void execute() {
        isExit();
    }

    /**
     * Hint to stop the bot from running.
     *
     * @return true to stop the bot from running
     */
    public boolean isExit() {
        return true;
    }
}
