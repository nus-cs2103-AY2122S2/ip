import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    ArrayList<Task> tasks;

    private Duke() {
        tasks = new ArrayList<>();
    }

    private void printTask(Task curr) {
        System.out.println(curr.getTaskIcon() + " [" + curr.getStatusIcon() + "]" + curr);
    }

    private void printNoOfTasks() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private void processNewTask(Task curr) {
        tasks.add(curr);
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
                for (int i = 1; i <= tasks.size(); i++) {
                    Task curr = tasks.get(i - 1);
                    System.out.print(i + ".");
                    printTask(curr);
                }
                System.out.println(bar);
            } else if (command.matches(".*\\bmark\\b.*")) {
                int number = Integer.parseInt(inputArray[1]);
                Task curr = tasks.get(number - 1);

                curr.setDone();
                System.out.println(bar);
                System.out.println("Nice! I've marked this task as done:");
                printTask(curr);
                System.out.println(bar);

            } else if (command.matches(".*\\bunmark\\b.*")) {
                int number = Integer.parseInt(inputArray[1]);
                Task curr = tasks.get(number - 1);

                curr.setUndone();
                System.out.println(bar);
                System.out.println("OK, I've marked this task as not done yet:");
                printTask(curr);
                System.out.println(bar);
            } else if (command.matches(".*\\btodo\\b.*")) {

                try {
                    if (inputArray.length <= 1) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                    System.out.println("Please try again:");
                    continue;
                }

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
            } else if (command.matches(".*\\bevent\\b.*")) {
                String metaInfo = originalInput.split("/at")[1];
                String strippedCommand = originalInput.replaceAll(".*\\bevent\\.*", "");
                System.out.println(bar);
                System.out.println("Got it. I've added this task:");
                Task curr = new Event(strippedCommand.split("/")[0], metaInfo);
                processNewTask(curr);
                System.out.println(bar);
            } else if (command.matches(".*\\bdelete\\b.*")) {
                int number = Integer.parseInt(inputArray[1]);

                try {
                    if (((number) <= 0) || ((number) > tasks.size())) {
                        throw new DukeException("Hey! That item does not exist!");
                    }
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                    System.out.println("Please try again:");
                    continue;
                }

                Task curr = tasks.get(number - 1);
                String message = curr.getTaskIcon() + " [" + curr.getStatusIcon() + "]" + curr;
                tasks.remove(curr);

                System.out.println(bar);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(message);
                printNoOfTasks();
                System.out.println(bar);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("Please try again:");
                continue;
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws DukeException {
        Duke kizer = new Duke();
        kizer.Run();
    }
}
