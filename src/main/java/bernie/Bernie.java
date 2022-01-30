package bernie;

import bernie.commands.CommandHandler;

public class Bernie {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.run();
    }
}
