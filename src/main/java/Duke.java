import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();
        System.out.println("Hello! I'm Whey\n" + "What can I do for you today?");
        Scanner userInput = new Scanner(System.in);
        try {
            while (true) {
                String nextLine = userInput.nextLine();
                int counter = 1;
                //Exiting the code when bye is inputted
                if (nextLine.equals("bye")) {
                    System.out.println("  " + "Bye beautiful! hope to see you again hehe");
                    break;
                //display the list of tasks
                } else if ((nextLine.replaceAll("\\s+", "")).equals("list")) {
                   System.out.println("Here are the tasks in your list:\n");
                    for (Task s : taskList) {
                        System.out.println("  " + counter + "." + s);
                        counter++;
                    }
                //nothing inputted into system.in
                } else if (nextLine.isBlank()) {
                    System.out.println("You didn't key in anything!! Feed me with a task!");
                //unmarking a task
                } else if (nextLine.startsWith("unmark")) {
                    int taskNumber = intSearch(nextLine) - 1;
                    if (taskNumber <= taskList.size()) {
                        Task intendedTask = taskList.get(taskNumber);
                        intendedTask.setDone(false);
                        System.out.println("  " + "Ok! I've marked this task as not done yet:\n"
                                + "    " + intendedTask);
                    } else {
                        System.out.println("Task does not exist! Check again hehe");
                    }
                //marking a task
                } else if (nextLine.startsWith("mark")) {
                    int taskNumber = intSearch(nextLine) - 1;
                    if (taskNumber <= taskList.size()) {
                        Task intendedTask = taskList.get(taskNumber);
                        intendedTask.setDone(true);
                        System.out.println("  " + "Nice! I've marked this task as done:\n"
                                + "    " + intendedTask);
                    } else {
                        System.out.println("Task does not exist! Check again hehe");
                    }
                //adding a task to the list
                } else {
                    Task currentTask = new Task(nextLine);
                    taskList.add(currentTask);
                    System.out.println("  " + "added: " + nextLine);
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Error detected, Whey is shutting down!");
        }
    }

    public static int intSearch(String text) {
        //replace non digit number with empty space
        text = text.replaceAll("[^\\d]", "");
        //replace every white space with empty space
        text = text.replaceAll(" +", "");
        if (text.equals("")) {
            return -1;
        }
        return Integer.parseInt(text);
    }
}
