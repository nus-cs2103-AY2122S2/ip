import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Heya! I'm Duke!\n" +
                "What can I do for ya?\n\n");

        // Create new variables
        String tempString;
        Echo tempEcho = new Echo();

        // While loop
        while(true) {

            tempString = scanner.nextLine();

            // Breaks the loop
            if (tempString.equals("bye")) {
                break;
            }

            tempEcho.EchoStr(tempString);
        }

        // The last thing Duke says
        System.out.println("  > Ok then, see ya!");
    }
}
