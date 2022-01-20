import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DM_DEFAULT_ENCODING")
public class Duke {
    public static void main(String[] args) {
        String line = "    ______________________________\n";

        // Greeting and Initialization
        System.out.println(line +
                "    Hello! I'm Bob!\n    What can I do for you?\n" + line);
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        String cmd = sc.nextLine();

        while (!cmd.equals("bye")) {

            // list functionality
            if (cmd.equals("list")) {
                int count = 1;
                System.out.println(line + "    Here are the tasks in your list:");
                for (Task entry : list) {
                    System.out.println("    " + count++ + "." + entry);
                }
                System.out.println(line);
            }
            // mark functionality
            else if (cmd.split(" ")[0].equals("mark")) {
                int idx = Integer.parseInt(cmd.split(" ")[1]);
                Task currTask = list.get(idx - 1);
                currTask.toggleDone();

                System.out.println(line + "    Nice! I've marked this task as done:\n      " + currTask);
                System.out.println(line);
            }
            // unmark functionality
            else if (cmd.split(" ")[0].equals("unmark")) {
                int idx = Integer.parseInt(cmd.split(" ")[1]);
                Task currTask = list.get(idx - 1);
                currTask.toggleNotDone();

                System.out.println(line + "    OK, I've marked this task as not done yet:\n      " + currTask);
                System.out.println(line);
            }
            // add functionality
            else {
                list.add(new Task(cmd));
                System.out.println(line + "    added: " + cmd + "\n" + line);
            }
            cmd = sc.nextLine();
        }
        System.out.println(line + "    Bye. Hope to see you again soon!\n" + line);
    }
}

