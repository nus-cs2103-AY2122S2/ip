import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");

        ArrayList<Task> taskHistory = new ArrayList<Task>();
        Scanner userInput = new Scanner(System.in);
        String nextInput = userInput.nextLine();

        while (!nextInput.equals("bye")) {
            String[] words = nextInput.split(" ", 2);
            String command = words[0];

            if (command.equals("list")) {
                int count = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task record : taskHistory) {
                    System.out.println(count + ". " + record.toString());
                    count++;
                }
            } else if (command.equals("deadline")) {
                String[] task = words[1].split("/by ");
                Deadline t = new Deadline(task[0], task[1]);
                taskHistory.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + taskHistory.size() + " tasks in the list.");
            } else if (command.equals("todo")) {
                String task = words[1];
                Todo t = new Todo(task);
                taskHistory.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + taskHistory.size() + " tasks in the list.");
            } else if (command.equals("event")) {
                String[] task = words[1].split("/at ");
                Event t = new Event(task[0], task[1]);
                taskHistory.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + taskHistory.size() + " tasks in the list.");
            } else if (command.equals("mark")) {
                int taskID = Integer.valueOf(words[1]);
                taskHistory.get(taskID -1).markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskHistory.get(taskID -1).toString());
            }else if (command.equals("unmark")) {
                int taskID = Integer.valueOf(words[1]);
                taskHistory.get(taskID -1).markUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(taskHistory.get(taskID -1).toString());
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            nextInput = userInput.nextLine();
        }
 
        System.out.println("Bye. Hope to see you again soon!");
    }
}
