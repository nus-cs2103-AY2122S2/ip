import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> tasks = new ArrayList<>();

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

            if (userInput.equals("bye")) {
                break;
            }

            if (userInput.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i));
                }
            } else {
                tasks.add(userInput);
                System.out.println("  Added:  " + userInput);
            }

            System.out.println("  ===================================");
        }

        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("  ===================================");

    }
}
