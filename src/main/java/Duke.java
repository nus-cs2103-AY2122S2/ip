import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "Hello! I'm Kizer\nWhat can I do for you?";
        String bar = "____________________________________________________________";
        System.out.println(bar);
        System.out.println(greeting);
        System.out.println(bar);
        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(bar);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(bar);
                sc.close();
                break;
            } else if (input.equals("list")) {
                System.out.println(bar);
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= taskList.size(); i++) {
                    Task curr = taskList.get(i - 1);
                    System.out.println(i + "." + "[" + curr.getStatusIcon() + "] " + curr);
                }
                System.out.println(bar);
            } else if (input.contains("mark")) {
                String numberOnly = input.replaceAll("[^0-9]", "");
                int number = Integer.parseInt(numberOnly);
                Task curr = taskList.get(number - 1);
                curr.markAsDone();

                System.out.println(bar);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + curr.getStatusIcon() + "] " + curr);
                System.out.println(bar);
            } else {
                System.out.println(bar);
                Task curr = new Task(input);
                taskList.add(curr);
                System.out.println("added: " + curr);
                System.out.println(bar);
            }

        }

    }
}
