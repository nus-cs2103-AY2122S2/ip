/**
 * Prints bye message, terminating command for Duke.main()
 */
public class ByeCommand extends Command {

    final String EXIT = "Duke terminated";

    @Override
    public void execute() {
        Ui.printExit();
    }
}
