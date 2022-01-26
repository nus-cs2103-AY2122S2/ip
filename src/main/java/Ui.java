import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER = "===================================================";
    private static final String LS = System.lineSeparator();

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        return fullInputLine;
    }

    public void showWelcome(String storageFilePath) {
        showToUser(
                Messages.MESSAGE_WELCOME,
                Messages.MESSAGE_FILE_PATH + storageFilePath,
                Messages.MESSAGE_PROMPT);
    }

    public void showExecutionMessage(String message) {
        showToUser(message);
    }

    public void showExecutionMessage(String message, String taskString, int size) {

        String displaySize = String.format("Now you have %s task%s in the list.", size, Parser.parseTaskSize(size));
        showToUser(message, taskString, displaySize);
    }

    public void showLoadingError(String message) {
        showToUser("Error loading Duke: " + message, DIVIDER);
    }

    public void showError(String message) {
        showToUser(message);
    }

    public void showLine() {
        showToUser(DIVIDER);
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m.replace("\n", LS));
        }
    }
}
