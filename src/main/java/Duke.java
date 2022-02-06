import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        // Init UI
        Ui ui = new Ui();

        // Init saved tasks
        try {
            TaskManager.loadTasks();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Init file if it is not there
        File f = new File("tasklist.txt");
        if (!f.isFile()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        // Printing Duke's intro
        Ui.printIntro();

        // Reading and processing inputs
        CommandFactory commandFactory = new CommandFactory();
        while (true) {
            Command nextCommand = commandFactory.makeCommand(ui.getNextLine()); // Creating the appropriate command
            nextCommand.execute();

            if (nextCommand instanceof ByeCommand) { // Check for exit command
                break;
            }
        }

        try {
            TaskManager.saveTaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if input is "bye"
     */
    private boolean isBye(String input) {

        return (input.equals("bye"));
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
