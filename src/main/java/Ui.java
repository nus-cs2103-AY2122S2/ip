import java.util.Scanner;

public class Ui {
    private static final String LOGO = "  _           _        \n"
            + " | |         | |       \n"
            + " | |    _   _| | _____ \n"
            + " | |   | | | | |/ / _ \\\n"
            + " | |___| |_| |   <  __/\n"
            + " |______\\__,_|_|\\_\\___|\n";

    private static final String BORDER = "=".repeat(60);
    private final Scanner inputSc;

    Ui() {
        inputSc = new Scanner(System.in);
    }

    public void greeting() {
        System.out.println("Hello! I am \n" + LOGO);
        showOutput("How can I help you?");
    }

    public void showOutput(String output) {
        String msg = String.format("%s\n%s\n%s", BORDER, output, BORDER);
        System.out.println(msg);
    }

    public void close() {
        inputSc.close();
    }

    public String readInput() {
        return inputSc.nextLine();
    }

    public void showError(String message) {
        showOutput(String.format("Oops, the force has encountered an error:\n%s", message));
    }
}
