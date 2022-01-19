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
                } else if (nextLine.startsWith("todo")) {
                    todo(nextLine, taskList);
                } else if (nextLine.startsWith("deadline")) {
                    deadline(nextLine, taskList);
                } else if (nextLine.startsWith("event")) {
                    event(nextLine, taskList);
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

    public static void todo(String text, ArrayList<Task> list) {
        String splicedString = text.substring(5);
        ToDo freshTodo = new ToDo(splicedString);
        list.add(freshTodo);
        System.out.println("   " + "Got it. I've added this task:\n"
                + "    " + freshTodo + "\n" +  "   Now you have " + list.size() + " tasks in the list.");
    }

    public static void deadline(String description, ArrayList<Task> list) {
        String[] splicedString = description.split(" /by ");
        String splicedDescription = splicedString[0].substring(9);
        String dueDate = splicedString[1];
        Deadline freshDeadline = new Deadline(splicedDescription, dueDate);
        list.add(freshDeadline);
        System.out.println("   " + "Got it. I've added this task:\n"
                + "    " + freshDeadline + "\n" +  "   Now you have " + list.size() + " tasks in the list.");
    }

    public static void event(String description, ArrayList<Task> list) {
        String[] splicedString = description.split(" /at ");
        String splicedDescription = splicedString[0].substring(6);
        String dueDate = splicedString[1];
        Event freshEvent = new Event(splicedDescription, dueDate);
        list.add(freshEvent);
        System.out.println("   " + "Got it. I've added this task:\n"
                + "    " + freshEvent + "\n" +  "   Now you have " + list.size() + " tasks in the list.");
    }
}
