import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {

        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nextLine = br.readLine();

        ArrayList<Task> list = new ArrayList<Task>();

        while (!nextLine.equals("bye")) {
            System.out.println("__________________________________________________");
            if (nextLine.split(" ")[0].equals("mark")) {
                int indexToMark = Integer.parseInt(nextLine.split(" ")[1]) - 1;
                list.set(indexToMark, list.get(indexToMark).mark());
                System.out.println("Nice! I've marked this task as done:\n  " 
                        + list.get(indexToMark));
            } else if (nextLine.split(" ")[0].equals("unmark")) {
                int indexToMark = Integer.parseInt(nextLine.split(" ")[1]) - 1;
                list.set(indexToMark, list.get(indexToMark).unmark());
                System.out.println("OK, I've marked this task as not done yet:\n  " 
                        + list.get(indexToMark));
            } else if (nextLine.equals("list")) {
                if (list.isEmpty()) {
                    System.out.println("Uh-oh! List is empty");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                }
            } else {
                String taskType = nextLine.split(" ")[0];
                Task task;
                if (taskType.equals("todo")) {
                    task = new Todo(nextLine.substring(5));
                } else if (taskType.equals("deadline")) {
                    String description = nextLine.split(" /by ")[0].substring(9);
                    String by = nextLine.split(" /by ")[1];
                    task = new Deadline(description, by);
                } else {
                    String description = nextLine.split(" /at ")[0].substring(6);
                    String at = nextLine.split(" /at ")[1];
                    task = new Event(description, at);
                }
                System.out.println("Got it. I've added this task:");
                list.add(task);
                System.out.println("  " + task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }

            System.out.println("__________________________________________________");
            nextLine = br.readLine();
        }

        System.out.println("__________________________________________________\n" 
                + "Bye. Hope to see you again soon!"
                + "\n__________________________________________________");
        br.close();
    }
}
