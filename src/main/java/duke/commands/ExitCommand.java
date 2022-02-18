package duke.commands;

/**
 * Command that indicates exit of program.
 */
public class ExitCommand extends Command{

    private static boolean isProgramRunning = true;

    /**
     * returns true if the program should be running and false otherwise.
     *
     * @return whether program should be running.
     */
    public static boolean isRunning() {
        return isProgramRunning;
    }

    /**
     * Sets isProgramRunning to false.
     *
     * @return Message for completing the command which is displayed to user.
     */
    @Override
    public String execute() {
        isProgramRunning = false;
        return "Till we meet again";
    }
}
