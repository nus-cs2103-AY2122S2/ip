import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "____________________________________________________________";
    private static final String SIGNATURE = " ____                         _____       _\n"
            + "| |_) |_   _ ___ ___ _   _  | (___   __ _| | ____ _\n"
            + "|  _ <| | | / __/ __| | | |  \\___ \\ / _` | |/ / _` |\\\n"
            + "| |_) | |_| \\__ \\__ \\ |_| |  ____) | (_| |   < (_| |\n"
            + "|____/ \\__,_|___/___/\\__, | |_____/ \\__,_|_|\\_\\__,_|\n"
            + "                      __/ |\n"
            + "                      |___/ \n";

    private static final String WELCOME_MESSAGE_ONE = "Tell me... have you seen a RED imposter among us?";
    private static final String WELCOME_MESSAGE_TWO = "If you have seen this SUSSY imposter,"
            + " please let me know immediately... otherwise how may I be of assistance?";
    private static final String GOODBYE_MESSAGE = "Better watch out for the imposter AMONG US!";
    protected static final String MARK_MESSAGE = "     The bar on the top left of your screen just increased!"
            + " Keep going!";
    protected static final String UNMARK_MESSAGE = "     Surely you aren't the imposter... right??";

    public static void line() {
        printMessage(DIVIDER);
    }

    public static void printMessage(String str) {
        System.out.println(str);
    }

    public void showWelcome() {
        printMessage(DIVIDER);
        printMessage(WELCOME_MESSAGE_ONE);
        printMessage(WELCOME_MESSAGE_TWO);
        printMessage(DIVIDER);
    }

    public void showGoodbye() {
        printMessage(DIVIDER);
        printMessage(GOODBYE_MESSAGE);
        printMessage(SIGNATURE);
        printMessage(DIVIDER);
    }

    public void showError(String msg) {
        printMessage(msg);
    }

    public void showDukeError(String msg) {
        printMessage(msg);
    }

    public String readCommand() throws EmptyInputException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        // Handle empty command
        if (input.equals("")) {
            throw new EmptyInputException();
        }
        return input;
    }
}
