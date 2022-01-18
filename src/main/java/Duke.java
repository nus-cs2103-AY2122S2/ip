import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Whey\n" + "What can I do for you today?");
        Scanner userInput = new Scanner(System.in);
        try {
            while (true) {
                String nextLine = userInput.nextLine();
                if (nextLine.equals("bye")) {
                    System.out.println("  " + "Bye beautiful! hope to see you again hehe");
                    break;
                } else {
                    System.out.println("  " + nextLine);
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Error detected, Whey is shutting down!");
        }
    }
}
