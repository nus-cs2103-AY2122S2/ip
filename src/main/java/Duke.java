import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Constant strings
        final String INTRO = "Hello! I'm Duke\n\tWhat can I do for you?";
        final String EXIT = "Bye. Hope to see you again soon!";

        // Init
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        System.out.println(duke.formatAnswer(INTRO));

        // Reading and processing inputs
        String nextCommand = sc.nextLine();
        while (!duke.isBye(nextCommand)) {
            System.out.println(duke.formatAnswer(nextCommand)); // Echo
            nextCommand = sc.nextLine(); // Update nextCommand
        }

        // Otherwise exit
        System.out.println(duke.formatAnswer(EXIT));


    }

    private String formatAnswer(String input) {
        /**
         * Prepares input for printing to System.out (adds top and bottom line)
         */

        // Variables
        String output;
        String line = "____________________________________________________________";

        // Output
        output = "\t" + line + "\n"
                + "\t" + input + "\n"
                + "\t" + line + "\n";

        return output;

    }

    private boolean isBye(String input) {
        /**
         * Checks if input is "bye"
         */
        return (input.equals("bye"));
    }
}
