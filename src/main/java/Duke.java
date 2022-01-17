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
        ArrayList<Task> listOfTasks = new ArrayList<>();
        String Header = "    ____________________________________________________________";
        // Repeat till bye is entered
        while (!userInput.equals("bye")) {

            if (userInput.equals("list")) {
                System.out.println(Header);
                System.out.println("    Here are the tasks in your list:");
                for (int index = 1; index < listOfTasks.size() + 1; index++) {
                    System.out.println("    " + index + "." + listOfTasks.get(index - 1));
                }
                System.out.println(Header);

            } else if (userInput.contains("unmark")) {
                int index = Character.getNumericValue(userInput.charAt(userInput.length() - 1) - 1);
                listOfTasks.get(index).unmark();
                String textToPrint = "     OK, I've marked this task as not done yet:\n"
                        + "       " + listOfTasks.get(index);
                textToPrint = Header + "\n" + textToPrint + "\n" + Header;
                System.out.println(textToPrint);

            } else if (userInput.contains("mark")) {
                int index = Character.getNumericValue(userInput.charAt(userInput.length() - 1) - 1);
                listOfTasks.get(index).mark();
                String textToPrint = "     Nice! I've marked this task as done:\n"
                        + "       " + listOfTasks.get(index);
                textToPrint = Header + "\n" + textToPrint + "\n" + Header;
                System.out.println(textToPrint);

            } else {
                // Echo current input
                String toPrint = "    ____________________________________________________________\n"
                               + "     added: " + userInput + "\n"
                               + "    ____________________________________________________________";

                // Create task object
                Task newTask = new Task(userInput);

                // Add the user input to list of tasks
                listOfTasks.add(newTask);

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
