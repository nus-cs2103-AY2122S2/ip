import java.util.*;

public class Duke {

    public static String indent = "    ";
    public static String separator = "--------------------------------------------";
    public static String[] openingMessage = new String[] {"Hello! I'm Duke", "What can I do for you?"};
    public static String closingMessage = "Bye. Hope to see you again soon!";

    public static void printIndent(String s) {
        System.out.println(indent + s);
    }

    public static void printWithSeparator(String message) {
        printIndent(separator);
        printIndent(message);
        printIndent(separator + "\n");
    }

    public static void printWithSeparator(String[] messages) {
        printIndent(separator);
        for (String message : messages) printIndent(message);
        printIndent(separator + "\n");
    }

    public static void main(String[] args) {
        String logo = indent + " ____        _        \n"
                + indent + "|  _ \\ _   _| | _____ \n"
                + indent + "| | | | | | | |/ / _ \\\n"
                + indent + "| |_| | |_| |   <  __/\n"
                + indent + "|____/ \\__,_|_|\\_\\___|\n";

        // introduction messages
        System.out.println(logo);
        printWithSeparator(openingMessage);

        // read input
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                printWithSeparator(closingMessage);
                break;
            }
            printWithSeparator(userInput);
        }

    }
}
