package bernie;

import bernie.commands.CommandHandler;

/**
 * Bernie, your personal tasks manager! :)
 */
public class Bernie {
    private final CommandHandler commandHandler;

    public Bernie() {
        this.commandHandler = new CommandHandler();
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
    /**
     * Returns the appropriate response based on the user input given
     * @param input String, the user input
     * @return String response
     */
    public String getResponse(String input) {
        String response = commandHandler.handle(input);
        return response;
    }
}
