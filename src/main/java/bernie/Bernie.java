package bernie;

import bernie.commands.CommandHandler;

/**
 * Bernie, your personal tasks manager! :)
 */
public class Bernie {
    /**
     * Runs Bernie to help you remember your tasks. CommandHandelr
     * is responsible for handling user inputs accordingly.
     * @param args String[]
     */
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.run();
    }
}
