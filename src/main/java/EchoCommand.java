/**
 * Repeats input to user
 */
public class EchoCommand extends Command {

    private String input;

    public EchoCommand (String input) {
        this.input = input;
    }
    @Override
    public void execute() {
        System.out.println(Duke.formatAnswer(input));
    }
}
