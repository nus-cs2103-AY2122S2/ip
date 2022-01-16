import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String botName = "Angry Kitty";
        printDivider();
        System.out.println("    Hello, I'm " + botName + ".");
        System.out.println("    What can I do for you?");
        printDivider();
        Scanner inputScanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = inputScanner.nextLine();
            if (input.equals("bye")) {
                printEndMessage();
            } else {
                echo(input);
            }
        }
    }

    public static void printEndMessage() {
        printDivider();
        System.out.println("    Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void printDivider() {
        String divider = "    ____________________________________________________________";
        System.out.println(divider);
    }

    public static void echo(String input) {
        printDivider();
        System.out.println("    " + input);
        printDivider();
    }

}
