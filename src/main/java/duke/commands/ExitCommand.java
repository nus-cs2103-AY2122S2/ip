package duke.commands;

public class ExitCommand extends Command{

    private static boolean isProgramRunning = true;

    public static boolean isRunning() {
        return isProgramRunning;
    }

    @Override
    public String execute() {
        isProgramRunning = false;
        return "Till we meet again";
    }
}
