import java.util.*;
import java.io.*;
/**
 * This program is used to add, list & mark the status of your current tasks.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Duke {
    // ArrayList to store all your tasks
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * The main method of this program
     */
    public static void main(String[] args) throws IOException {
        // Greet
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        // Scanner Object
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Add different types of Task, List, Mark
        while (!input.equals("bye")) {
            int num = 1;
            String[] temp = input.split(" ");
            if (temp[0].equals("mark")) {
                int index = Integer.parseInt(temp[1]) - 1;
                Task task = list.get(index);
                task.setAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.getTask());
            } else if (temp[0].equals("unmark")) {
                int index = Integer.parseInt(temp[1]) - 1;
                Task task = list.get(index);
                task.setAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task.getTask());
            } else if (temp[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (Task task : list) {
                    System.out.println(num + "." + task.getTask());
                    num++;
                }
            } else {
                if (temp[0].equals("todo")) {
                    Task newTask = new ToDo(Duke.rmType(input));
                    list.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.getTask());
                    System.out.println("Now you have "+ list.size() +" tasks in the list.");
                } else if (temp[0].equals("deadline")) {
                    String adjInput = Duke.rmType(input);
                    Task newTask = new Deadline(Duke.extractDateORTask(adjInput, 2),
                            Duke.extractDateORTask(adjInput, 1));
                    list.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.getTask());
                    System.out.println("Now you have "+ list.size() +" tasks in the list.");
                } else if (temp[0].equals("event")) {
                    String adjInput = Duke.rmType(input);
                    Task newTask = new Event(Duke.extractDateORTask(adjInput, 2),
                            Duke.extractDateORTask(adjInput, 1));
                    list.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.getTask());
                    System.out.println("Now you have "+ list.size() +" tasks in the list.");
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
            input = scanner.nextLine();
        }
        //Exit
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * This method is used to remove the task type from input
     *
     * @param input string given by the user
     * @return remaining string after task type is removed.
     */
    public static String rmType(String input) {
        String[] temp = input.split(" ");
        String[] newTemp = new String[temp.length - 1];
        for (int i = 1; i < temp.length; i++) {
            newTemp[i - 1] = temp[i];
        }
        String adjInput = String.join(" ", newTemp);
        return adjInput;
    }

    /**
     * This method is used to extract date or task depending on the integer
     * passed into the method. Do note that the input string passed in should
     * be the result from rmType method.
     * 1 denotes date, 2 denotes task name
     *
     * @param input string given by the user
     * @return remaining string after task type is removed.
     */
    public static String extractDateORTask(String input, int integer) {
        String[] temp = input.split(" /");
        if (integer == 1) {
            return temp[1];
        } else {
            return temp[0];
        }
    }
}
