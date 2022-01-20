import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);

        System.out.println("What can I do for you?");
        while (true) {
            String userInput = scanner.nextLine();
            System.out.println("  ===================================");

            if(userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("  ===================================");
                break;
            }

            System.out.println("    " + userInput);
            System.out.println("  ===================================");
        }
    }
}
