import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "    ____________________________________________________________\n"
                        + "     Hello! I'm Duke\n"
                        + "     What can I do for you?\n"
                        + "    ____________________________________________________________";
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);

        String userInput = sc.nextLine();
        ArrayList<String> listOfTasks = new ArrayList<>();

        // Repeat till bye is entered
        while (!userInput.equals("bye")) {

            if (userInput.equals("list")) {
                String toPrint = "    ____________________________________________________________";
                System.out.println(toPrint);
                for (int index = 1; index < listOfTasks.size() + 1; index++) {
                    System.out.println("     " + index + ". " + listOfTasks.get(index - 1));
                }
                System.out.println(toPrint);
            } else {
                // Echo current input
                String toPrint = "    ____________________________________________________________\n"
                               + "     added: " + userInput + "\n"
                               + "    ____________________________________________________________";

                // Add the user input to list of tasks
                listOfTasks.add(userInput);

                System.out.println(toPrint);
            }

            // Get next input
            userInput = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________\n"
                         + "     Bye. Hope to see you again soon!\n"
                         + "    ____________________________________________________________");

    }
}
