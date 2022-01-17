import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(intro);
        ArrayList<Task> tasks = new ArrayList<>();
        String cmd = sc.next();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i - 1));
                }
            } else if (cmd.equals("mark")) {
                int taskNumber = sc.nextInt();
                tasks.get(taskNumber - 1).mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskNumber - 1));
            } else if (cmd.equals("unmark")) {
                int taskNumber = sc.nextInt();
                tasks.get(taskNumber - 1).unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(taskNumber - 1));
            } else if (cmd.equals("add")){
                System.out.println("What task do you want to add? ");
                sc.nextLine();
                String task = sc.nextLine();
                tasks.add(new Task(task));
                System.out.println("added: " + task);
            }
            cmd = sc.next();

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
