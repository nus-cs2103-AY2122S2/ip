import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Meep {
    public static void main(String[] args) {
        Utils.printLogo();
        String userInput = "In";
        String outputMsg = "Out";

        FastIO sc = new FastIO();

        List<Task> taskList = new ArrayList<>();

        while (true) {
            System.out.print("You: ");
            userInput = sc.nextLine();
            String[] arr = userInput.split(" ", 2);
            String userCommand = arr[0];


            if (userCommand.equals("bye")) {
                System.out.println("Meep: Bye. Hope to see you again soon!");
                break;
            } else if (userCommand.equals("list")) {
                System.out.println("Meep: ");
                int i = 1;
                for (Task task : taskList) {
                    System.out.println("     " + i + ".  " + task.toString());
                    i++;
                }
            } else if (userCommand.equals("mark")) {
                String theRest = arr[1];
                Task task = taskList.get(Integer.parseInt(theRest) - 1);
                task.markDone();

                System.out.println("Meep: Nice! I've marked this task as done: ");
                System.out.println("     " + task.toString());
            } else if (userCommand.equals("unmark")) {
                String theRest = arr[1];
                Task task = taskList.get(Integer.parseInt(theRest) - 1);
                task.unmarkDone();

                System.out.println("Meep:  OK, I've marked this task as not done yet: ");
                System.out.println("     " + task.toString());
            } else if (userCommand.equals("deadline")) {
                String[] output = arr[1].split("/", 2);
                String[] newoutput = output[1].split(" ", 2);
                Deadline deadline = new Deadline(output[0],newoutput[1]);
                taskList.add(deadline);

                System.out.println("Meep: Got it. I've added this task: ");
                System.out.println(deadline.toString());
                System.out.println("Now you have "+ taskList.size()+" tasks in the list.");
            } else if (userCommand.equals("event")) {
                String[] output = arr[1].split("/", 2);
                String[] newoutput = output[1].split(" ", 2);
                Event event = new Event(output[0],newoutput[1]);
                taskList.add(event);

                System.out.println("Meep: Got it. I've added this task: ");
                System.out.println(event.toString());
                System.out.println("Now you have "+ taskList.size()+" tasks in the list.");
            } else if (userCommand.equals("todo")) {
                outputMsg = arr[1];
                ToDo todo = new ToDo(outputMsg);
                taskList.add(todo);

                System.out.println("Meep: Got it. I've added this task: ");
                System.out.println(todo.toString());
                System.out.println("Now you have "+ taskList.size()+" tasks in the list.");
            } else {
                System.out.println("Meep: else ");
            }
        }
        sc.close();
    }
}