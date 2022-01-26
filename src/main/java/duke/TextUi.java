package duke;
import java.util.Scanner;

public class TextUi {
    private final Scanner scanner;
    private static final String DIVIDER = "===================================================";
    private static final String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public TextUi() {
        scanner = new Scanner(System.in);
    }

    public void greeting() {
        System.out.println("Konnichiwassup from\n" + logo);
        showDivider();
        System.out.println("What do you need help with?\n");
    }

    public void showDivider() {
        System.out.println(DIVIDER);
    }

    public void sayBye() {
        System.out.println("Sayonara! Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("Something went wrong with the program!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void closeScanner() { scanner.close(); }
}
