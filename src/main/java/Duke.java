import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider = "______________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Konnichiwassup from\n" + logo);
        Scanner scanner = new Scanner(System.in);


        System.out.println(divider);
        System.out.println("What do you need help with?\n");
        String userInput = scanner.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(userInput);
            System.out.println(divider);
            userInput = scanner.nextLine();
        }

        System.out.println(divider);
        System.out.println("Sayonara! Hope to see you again soon!");
    }
}
