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
        ArrayList<String> storageList = new ArrayList<>();
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
            } else {
                System.out.println(bar);
                // storageList.add(input);
                Task curr = new Task(input);
                taskList.add(curr);
                System.out.println("added: " + curr);
                System.out.println(bar);
            }

        }

    }
}
