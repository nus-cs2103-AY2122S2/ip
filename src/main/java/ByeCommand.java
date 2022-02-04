/**
 * Prints bye message, terminating command for Duke.main()
 */
public class ByeCommand extends Command {

    final String EXIT = "Duke terminated";

    //TODO: Deprecate, Ui manages this
    private String formatOutput() {
        String formattedBye = Duke.indent(EXIT, 1);
        String finalFormatted = Duke.formatLines(formattedBye);

        return finalFormatted;
    }

    @Override
    public void execute() {
        Ui.printExit();
    }
}
