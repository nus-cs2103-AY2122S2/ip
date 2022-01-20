/**
 * Prints bye message, terminating command for Duke.main()
 */
public class ByeCommand extends Command {

    final String EXIT = "Bye. Hope to see you again soon!";
    @Override
    public void execute() {
        System.out.println(Duke.formatAnswer(EXIT));
    }
}
