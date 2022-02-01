package duke.command;

/**
 * ByeCommand class
 */
public class ByeCommand extends Command<String> {

    /**
     * Constructor for ByeCommand object
     */
    public ByeCommand() {
        runCommand();
    }

    /**
     * change the status of the program
     */
    public void runCommand() {
        super.changeRunning(false);
    }
}
