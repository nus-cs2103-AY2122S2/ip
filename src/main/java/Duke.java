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
                // Get index of task
                int index = Character.getNumericValue(userInput.charAt(userInput.length() - 1) - 1);

                listOfTasks.get(index).unmark();
                String textToPrint = "     OK, I've marked this task as not done yet:\n"
                        + "       " + listOfTasks.get(index);
                System.out.println(printWithin(textToPrint));

            } else if (userInput.contains("mark")) {
                // Get index of task
                int index = Character.getNumericValue(userInput.charAt(userInput.length() - 1) - 1);

                listOfTasks.get(index).mark();
                String textToPrint = "     Nice! I've marked this task as done:\n"
                        + "       " + listOfTasks.get(index);
                System.out.println(printWithin(textToPrint));

            } else if (userInput.contains("deadline")) {
                // Extract the Task and the deadline
                int index = userInput.indexOf('/') + 3;
                String date = userInput.substring(index);
                String task = userInput.substring(9, index - 4);

                // Create Deadline object
                Task newTask = new DeadLines(task, date);
                // Add to list of tasks
                listOfTasks.add(newTask);

                String textToPrint = "     Got it. I've added this task:\n"
                        + "       " + newTask + "\n" + "     Now you have " + listOfTasks.size() + " tasks in the list.";
                System.out.println(printWithin(textToPrint));

            } else if (userInput.contains("event")) {
                // Extract the Task and the deadline
                int index = userInput.indexOf('/') + 3;
                String date = userInput.substring(index);
                String task = userInput.substring(6, index - 4);

                // Create Deadline object
                Task newTask = new Event(task, date);
                // Add to list of tasks
                listOfTasks.add(newTask);

                String textToPrint = "     Got it. I've added this task:\n"
                        + "       " + newTask + "\n" + "     Now you have " + listOfTasks.size() + " tasks in the list.";
                System.out.println(printWithin(textToPrint));

            } else {
                // Create task object
                Task newTask = new ToDos(userInput.substring(5));
                // Add the user input to list of tasks
                listOfTasks.add(newTask);

                String textToPrint = "     Got it. I've added this task:\n"
                        + "       " + newTask + "\n" + "     Now you have " + listOfTasks.size() + " tasks in the list.";
                System.out.println(printWithin(textToPrint));
            }

            // Get next input
            userInput = sc.nextLine();
        }

        System.out.println(printWithin("     Bye. Hope to see you again soon!"));

    }

    public static String printWithin(String str) {
        String Header = "    ____________________________________________________________";
        return Header + "\n" + str + "\n" + Header;
    }

}


