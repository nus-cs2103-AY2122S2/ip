import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static final String EXCLAMATION = "!";
    public static final String DIVIDER = "================================================================";

    public static final String LINE_PREFIX = "|| ";

    public static final String BOT_NAME = "Feline";

    public static final String COMMANDS = "list, todo, deadline (using /by)," +
                                        " event (using /at), mark, unmark, delete";

    public String readCommand() {
        return scanner.nextLine();
    }

    public void divide() {
        showToUser(DIVIDER);
    }

    public void greet() {
        showToUser(DIVIDER, Messages.WELCOME_MESSAGE);
    }

    public void farewell() {
        showToUser(DIVIDER, Messages.FAREWELL_MESSAGE);
        scanner.close();
    }

    public void showCommands() {
        showToUser(DIVIDER, Messages.UNKNOWN_COMMAND, COMMANDS);
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        showToUser(message);
    }

    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m.replace("\n", LINE_SEPARATOR + LINE_PREFIX));
        }
    }
}