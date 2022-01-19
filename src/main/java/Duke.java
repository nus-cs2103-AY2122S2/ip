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

        // Add, List, Mark
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
                for (Task task : list) {
                    System.out.println(num + "." + task.getTask());
                    num++;
                }
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        }
        //Exit
        System.out.println("Bye. Hope to see you again soon!");
    }
}
