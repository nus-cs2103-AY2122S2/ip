package duke;
import java.util.Scanner;

public class TextUi {
    private final Scanner scanner;

    /**
     * Instantiates a ui object
     */
    public TextUi() {
        scanner = new Scanner(System.in);
    }

    private static final String DIVIDER = "===================================================";
    private static final String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Method that greets the user when the file first runs
     */
    public void greeting() {
        System.out.println("Konnichiwassup from\n" + logo);
        showDivider();
        System.out.println("What do you need help with?\n");
    }

    /**
     * Method that shows a divider to the user
     */
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Method that says bye to the user
     */
    public void sayBye() {
        System.out.println("Sayonara! Hope to see you again soon!");
    }


    /**
     * Method that shows loading error if there is something wrong the program
     */
    public void showLoadingError() {
        System.out.println("Something went wrong with the program!");
    }

    /**
     * Method that returns the next input that the user has keyed in
     * @return String consisting of the next input of the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Method that closes the scanner when the program has finished executing
     */
    public void closeScanner() {
        scanner.close();
    }
}
