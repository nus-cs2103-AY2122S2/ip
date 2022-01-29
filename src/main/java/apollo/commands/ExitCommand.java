package apollo.commands;

public class ExitCommand extends Command {

    @Override
    public String execute() {
        return "See you next time. \nI am always available when you need me. ";
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
