import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {
        // Introduction
        System.out.println("Hello! I'm Duck\nWhat can I do for you? *quack*");

        // Scanner for reading user input
        Scanner sc = new Scanner(System.in);
        String command = ""; //Where user input will be stored

        // Storage for all the items listed by the user
        List<String> storeList = new ArrayList<String>();
        List<Boolean> storeDone = new ArrayList<Boolean>();


        // Program will keep taking in new user input until terminated
        while (true) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            command = st.nextToken();

            // User terminates the program
            if (command.equals("bye"))  {
                System.out.println("Aww. Hope to see you again soon! *quack*");
                return;
            }
            // User lists out all stored items
            else if (command.equals("list")) {
                System.out.println("These are your stored items *quack*:");
                for (int i = 0; i < storeList.size(); i++) {
                    int number = i + 1;
                    String state = storeDone.get(i) ? "X" : " ";
                    String task = storeList.get(i);
                    System.out.println(String.format("%d.[%s] %s", number, state, task));
                }
            }
            // User marks an item as done
            else if (command.equals("mark")) {
                // Error handling if user does not input a second argument
                if (!st.hasMoreTokens()) {
                    System.out.println("Please input an item number when marking (eg. 'mark 1')");
                } else {
                    // Error handling if user inputs strings
                    try {
                        int number = Integer.parseInt(st.nextToken());
                        storeDone.set(number - 1, true);
                        System.out.println(String.format("I've marked task %d as done! *quack*", number));
                    } catch (Exception e) {
                        System.out.println("Please ONLY input integers when unmarking (eg. 'unmark 1')");
                    }
                }
            }
            // User marks an item as undone
            else if (command.equals("unmark")) {
                // Error handling if user does not input a second argument
                if (!st.hasMoreTokens()) {
                    System.out.println("Please input an item number when unmarking (eg. 'unmark 1')");
                } else {
                    // Error handling if user inputs strings
                    try {
                        int number = Integer.parseInt(st.nextToken());
                        storeDone.set(number - 1, false);
                        System.out.println(String.format("I've unmarked task %d as done! *quack*", number));
                    } catch (Exception e) {
                        System.out.println("Please ONLY input integers when unmarking (eg. 'unmark 1')");
                    }
                }
            }
            // User adds items to the list
            else {
                storeList.add(command);
                storeDone.add(false);
                System.out.println(String.format("You have added: %s", command));
            }
        }
    }
}
