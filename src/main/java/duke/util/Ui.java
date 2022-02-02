package duke.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Represents the user interaction function of the program.
 */
public class Ui {
    // Divider and indentation for design purpose
    private static final String curlyLine = "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static final String dashLine = "    -------------------------------------------------------------\n";
    private static final String indent = "    ";

    private static final String logo = "     ____        _\n"
            + "    |  _ \\ _   _| | _____\n"
            + "    | | | | | | | |/ / _ \\\n"
            + "    | |_| | |_| |   <  __/\n"
            + "    |____/ \\__,_|_|\\_\\___|\n";

    private static final String intro = indent + "Hello! I'm Duke!\n" + indent + "What can I do for you?";

    private BufferedReader br;

    /**
     * Class constructor.
     */
    public Ui() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Returns the user input.
     *
     * @return String input by user.
     * @throws IOException If input cannot be read.
     */
    public String readInput() throws IOException {
        return br.readLine();
    }

    /**
     * Returns the divider line.
     *
     * @return Line of style ~~~.
     */
    public String showLine() {
        return curlyLine;
    }

    /**
     * Returns the default indentation of output.
     *
     * @return Indentation of four spaces.
     */
    public String showIndent() {
        return indent;
    }

    /**
     * Prints the greeting message when user first runs program.
     */
    public void showGreeting() {
        System.out.print(logo + "\n" + intro + "\n" + dashLine);
    }

    /**
     * Prints the error message with indentation and lines.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        System.out.print(curlyLine + indent + message + "\n" + curlyLine);
    }

    /**
     * Prints the output as a result of a user command with indentaion and lines.
     *
     * @param message The output to be shown.
     */
    public void printOutput(String message) {
        System.out.print(curlyLine + indent + message + "\n" + curlyLine);
    }
}
