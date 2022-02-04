import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Constant strings
        final String INTRO = "Duke initialised";

        // Init text scanner
        Scanner sc = new Scanner(System.in);

        // Init saved tasks
        try {
            TaskManager.loadTasks();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Init file if it is not there
        File f = new File("./tasklist.txt");
        if (!f.isFile()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        // Printing Duke's intro
        String formattedIntroText = Duke.indent(INTRO, 1);
        String formattedIntro = Duke.formatLines(formattedIntroText);
        System.out.println(formattedIntro);

        // Reading and processing inputs
        String nextLine = sc.nextLine();
        CommandFactory commandFactory = new CommandFactory();
        while (true) {
            Command nextCommand = commandFactory.makeCommand(nextLine); // Creating the appropriate command
            nextCommand.execute();

            if (nextCommand instanceof ByeCommand) { // Check for exit command
                break;
            }
            nextLine = sc.nextLine(); // Update nextLine variable with next input
        }
    }

    /**
     * Checks if input is "bye"
     */
    private boolean isBye(String input) {

        return (input.equals("bye"));
    }

    /**
     * Formats the given input between lines
     *
     * @param input Formatted input with proper indents and newlines.
     */
    public static String formatLines(String input) {
        String output = "";
        output += Duke.line();
        output += input;
        output += "\n" + Duke.line();

        return output;
    }

    /**
     * Provides a formatting line for Duke's responses
     *
     * @return A formatted line
     */
    public static String line() {
        String line = "\t____________________________________________________________\n";
        return line;
    }

    /**
     * Formats a single line with a new line
     *
     * @param input Input string
     * @return String with newline
     */
    public static String newLine(String input) {
        String output = input + "\n";
        return output;
    }

    /**
     * Formats a single line with the specified number of indentations
     *
     * @param input   Input string
     * @param indents Number of indents to add
     * @return String with specified number of indents
     */
    public static String indent(String input, int indents) {
        String indent = "";
        String output = "";

        // Adding the appropriate number of indents
        for (int i = 0; i < indents; i++) {
            indent += "\t";
        }

        // Forming the final string
        output = indent + input;

        return output;


    }
}
