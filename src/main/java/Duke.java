import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
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
            try {
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
                    // Throw error is index is -1 or if no date is provided
                    if (index == 2 || index > userInput.length()) {
                        throw new DukeException("     ☹ Format for deadline not followed (Please include a '/' and provide a date)");
                    }
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
                    // Throw error is index is -1 or if no date is provided
                    if (index == 2 || index > userInput.length()) {
                        throw new DukeException("     ☹ Format for event not followed (Please include a '/' and provide a date)");
                    }
                    String date = userInput.substring(index);
                    String task = userInput.substring(6, index - 4);

                    // Create Deadline object
                    Task newTask = new Event(task, date);
                    // Add to list of tasks
                    listOfTasks.add(newTask);

                    String textToPrint = "     Got it. I've added this task:\n"
                            + "       " + newTask + "\n" + "     Now you have " + listOfTasks.size() + " tasks in the list.";
                    System.out.println(printWithin(textToPrint));

                } else if (userInput.contains("todo")){
                    // Create task object
                    Task newTask = new ToDos(userInput.substring(4));
                    // Add the user input to list of tasks
                    listOfTasks.add(newTask);

                    String textToPrint = "     Got it. I've added this task:\n"
                            + "       " + newTask + "\n" + "     Now you have " + listOfTasks.size() + " tasks in the list.";
                    System.out.println(printWithin(textToPrint));

                } else {
                        throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e){
                System.out.println(printWithin(e.getMessage()));
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


