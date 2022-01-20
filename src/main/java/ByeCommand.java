/**
 * Prints bye message, terminating command for Duke.main()
 */
public class ByeCommand extends Command {

    final String EXIT = "Bye. Hope to see you again soon!";

    private String formatOutput() {
        String formattedBye = Duke.indent(EXIT, 1);
        String finalFormatted = Duke.formatLines(formattedBye);

        return finalFormatted;
    }
    @Override
    public void execute() {
        // Console prints
        String output = formatOutput();

        System.out.println(output);

    }
}
