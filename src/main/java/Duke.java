import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Introduction
        System.out.println("Hello! I'm Duck\nWhat can I do for you? *quack*");

        // Scanner for reading user input
        Scanner sc = new Scanner(System.in);
        String command = ""; //Where user input will be stored

        // Storage for all the items listed by the user
        List<String> storeList = new ArrayList<String>();

        // Program will keep taking in new user input until terminated
        while (true) {
            command = sc.nextLine();

            // User terminates the program
            if (command.equals("bye"))  {
                System.out.println("Aww. Hope to see you again soon! *quack*");
                return;
            }
            // User lists out all stored items
            else if (command.equals("list")) {
                System.out.println("These are your stored items *quack*:");
                for (int i = 0; i < storeList.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, storeList.get(i)));
                }
            }
            // User adds items to the list
            else {
                storeList.add(command);
                System.out.println(String.format("You have added: %s", command));
            }
        }
    }
}
