package duke.command;

/**
 * ByeCommand class
 */
public class ByeCommand extends Command<String> {

    /**
     * Constructor for ByeCommand object that causes the program to exit
     */
    public ByeCommand() {
        runCommand();
    }

    /**
     * changes the status of the program
     */
    public void runCommand() {
        super.changeRunning(false);
    }
}
