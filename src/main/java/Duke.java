import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Constant strings
        final String INTRO = "Hello! I'm Duke\n\tWhat can I do for you?";

        // Init
        Scanner sc = new Scanner(System.in);
        System.out.println(Duke.formatAnswer(INTRO));

        // Reading and processing inputs
        String nextLine = sc.nextLine();
        CommandFactory commandFactory = new CommandFactory();
        while (true) {
            Command nextCommand = commandFactory.makeCommand(nextLine); // Creating the appropriate command
            nextCommand.execute();
            if (nextCommand instanceof ByeCommand) { // Check for exit command
                break;
            }
            nextLine = sc.nextLine();
        }

    }

    /**
     * Prepares input for printing to System.out (adds top and bottom line)
     */
    public static String formatAnswer(String input) {

        // Variables
        String output;
        String line = "____________________________________________________________";

        // Output
        output = "\t" + line + "\n"
                + "\t" + input + "\n"
                + "\t" + line + "\n";

        return output;

    }

    /**
     * Checks if input is "bye"
     */
    private boolean isBye(String input) {

        return (input.equals("bye"));
    }
}
