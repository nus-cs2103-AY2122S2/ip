import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

            // add functionalities
            if (cmd.split(" ")[0].equals("todo") || cmd.split(" ")[0].equals("deadline") ||
                    cmd.split(" ")[0].equals("event")) {
                Task newTask;
                cmd = cmd.strip();

                if (cmd.split(" ")[0].equals("todo")) {
                    newTask = new ToDo(cmd.substring(cmd.indexOf(" ") + 1));
                } else if (cmd.split(" ")[0].equals("deadline")) {
                    String by = cmd.split("/by ")[1];
                    String desc = cmd.split("/by ")[0];
                    desc = desc.substring(desc.indexOf(" ")).strip();
                    newTask = new Deadline(desc, by);
                } else {
                    String time = cmd.substring(cmd.indexOf("/at ") + 4);
                    String desc = cmd.split("/at ")[0].strip();
                    desc = desc.substring(desc.indexOf(" ")).strip();
                    newTask = new Event(desc, time);
                }
                list.add(newTask);
                System.out.println(line + "    Got it. I've added this task:\n      " + newTask);
                System.out.println("    Now you have " + list.size() + " tasks in the list.\n" + line);
            }

            // list functionality
            else if (cmd.equals("list")) {
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
            cmd = sc.nextLine();
        }
        System.out.println(line + "    Bye. Hope to see you again soon!\n" + line);
    }
}

