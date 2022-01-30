package apollo.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static apollo.messages.Messages.LINE_FEED;

/**
 * Class for {@code Ui}, user interface for Apollo.
 */
public class Ui {

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructor for {@code Ui}.
     *
     * @param in To scan for user input from.
     * @param out To print to.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Constructor with default InputStream and PrintStream.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Gets next line of user input from InputStream.
     *
     * @return Line of user input.
     */
    public String getUserCommand() {
        return in.nextLine().trim();
    }

    /**
     * Wraps supplied message with borders and prints to PrintStream.
     *
     * @param message To print to PrintStream.
     */
    public void printMessage(String message) {
        String border = "\t_______________________________"
                + "_________________________________________\n";
        String wrapMessage = border + "\t "
                + message.replace("\n", "\n\t ")
                + "\n" + border;
        out.print(wrapMessage.replace("\n", LINE_FEED));
    }
}
