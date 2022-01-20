/**
 * Repeats input to user
 */
public class EchoCommand extends Command {

    private String input;

    public EchoCommand (String input) {
        this.input = input;
    }

    public String formatOutput (String input) {
        String formattedInput = Duke.indent(input, 1);
        String finalFormatted = Duke.formatLines(formattedInput);

        return finalFormatted;
    }
    @Override
    public void execute() {

        // Console prints
        String output = formatOutput(input);
        System.out.println(output);
    }
}
