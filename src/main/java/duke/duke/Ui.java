package duke.duke;
import java.util.Scanner;

public class Ui {

    private final String line = "------------------------------------";

    public Ui() {
    }

    /**
     * Prints the logo of Duke when called, as well as a simple welcome
     * message.
     */

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I help you today?");
        System.out.println(line);
    }

    /**
     * Prints a line when called
     */

    public void showLine() {
        System.out.println(line);
    }

    /**
     * Returns user input when called.
     *
     * @return User input.
     */

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
