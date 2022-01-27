package ann.commands;

public class ExitCommand extends Command{

    public ExitCommand() {

    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public void executeCommand() {
        super.setMessage("Sad to see you go :( See you again soon!");
    }
}
