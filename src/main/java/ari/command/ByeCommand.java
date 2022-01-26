package main.java.ari.command;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public String execute() {
        // Print out good bye message
        return "Have a nice day Master";
    }

    public static boolean isGoodBye(Command command) {
        return command instanceof ByeCommand;
    }

}
