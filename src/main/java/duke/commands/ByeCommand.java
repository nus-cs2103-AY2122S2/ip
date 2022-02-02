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

    /**
     * Runs this command to tell the program to end the app.
     *
     * @return a empty string
     */
    public String execute() {
        isExit();
        return "";
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
