import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    ArrayList<Task> taskList;

    private Duke() {
        taskList = new ArrayList<>();
    }

    private void printTask(Task curr) {
        System.out.println(curr.getTaskIcon() + " [" + curr.getStatusIcon() + "] " + curr);
    }

    private void printNoOfTasks() {
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    private void processNewTask(Task curr) {
        taskList.add(curr);
        printTask(curr);
        printNoOfTasks();
    }

    private void Run() {
        Scanner sc = new Scanner(System.in);

        String greeting = "Hello! I'm Kizer\nWhat can I do for you?";
        String bar = "____________________________________________________________";
        System.out.println(bar);
        System.out.println(greeting);
        System.out.println(bar);

        while (true) {
            String originalInput = sc.nextLine();
            String[] inputArray = originalInput.split(" ");
            String command = inputArray[0];
            if (command.matches(".*\\bbye\\b.*")) {
                System.out.println(bar);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(bar);
                sc.close();
                break;
            } else if (command.matches(".*\\blist\\b.*")) {
                System.out.println(bar);
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= taskList.size(); i++) {
                    Task curr = taskList.get(i - 1);
                    System.out.print(i + ".");
                    printTask(curr);
                }
                System.out.println(bar);
            } else if (command.matches(".*\\bmark\\b.*")) {
                // String numberOnly = command.replaceAll("[^0-9]", "");
                // int number = Integer.parseInt(numberOnly);
                int number = Integer.parseInt(inputArray[1]);
                Task curr = taskList.get(number - 1);

                curr.markAsDone();
                System.out.println(bar);
                System.out.println("Nice! I've marked this task as done:");
                printTask(curr);
                System.out.println(bar);

            } else if (command.matches(".*\\bunmark\\b.*")) {
                // String numberOnly = command.replaceAll("[^0-9]", "");
                // int number = Integer.parseInt(numberOnly);
                int number = Integer.parseInt(inputArray[1]);
                Task curr = taskList.get(number - 1);

                curr.markAsUndone();
                System.out.println(bar);
                System.out.println("OK, I've marked this task as not done yet:");
                printTask(curr);
                System.out.println(bar);
            } else if (command.matches(".*\\btodo\\b.*")) {
                System.out.println(bar);
                System.out.println("Got it. I've added this task:");
                Task curr = new Todo(originalInput.replaceAll(".*\\btodo\\.*", ""));
                processNewTask(curr);
                System.out.println(bar);
            } else if (command.matches(".*\\bdeadline\\b.*")) {
                String metaInfo = originalInput.split("/by")[1];
                String strippedCommand = originalInput.replaceAll(".*\\bdeadline\\.*", "");
                System.out.println(bar);
                System.out.println("Got it. I've added this task:");
                Task curr = new Deadline(strippedCommand.split("/")[0], metaInfo);
                processNewTask(curr);
                System.out.println(bar);
            } else {
                System.out.println("no command given");
            }

        }

    }

    public static void main(String[] args) {
        Duke kizer = new Duke();
        kizer.Run();
    }
}
