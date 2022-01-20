import java.lang.*;
import java.util.*;

public class Duke {
    static String indent = "     ";
    static String line = "    ____________________________________________________________";

    public static void main(String[] args) {
        // Greeting
        System.out.println(line);
        System.out.println(indent+"Hello! I'm Duke\n"+
                indent+
                "What can I do for you?");
        System.out.println(line);
        System.out.println();

        // Data Structures
        Map<Integer, Task> commands = new HashMap<>();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            StringTokenizer st = new StringTokenizer(input);
            System.out.println(line);
            String cmd = st.nextToken();

            if (cmd.equals("list")) {
                System.out.println(indent+"Here are the tasks in your list:");
                for (int i=1; i<=commands.size(); i++) {
                    System.out.println(indent+i+"."+commands.get(i));
                }
            } else if (cmd.equals("mark") || cmd.equals("unmark")) {
                int rank = Integer.parseInt(st.nextToken());
                commands.get(rank).setDone(cmd.equals("mark"));
                if (cmd.equals("mark")) {
                    System.out.println(indent + "Nice! I've marked this task as done:");
                } else {
                    System.out.println(indent + "OK, I've marked this task as not done yet:");
                }
                System.out.println(indent + "  " + commands.get(rank));
            } else if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {

                System.out.println(indent+"Got it. I've added this task:");
                switch (cmd) {
                    case "todo":
                        commands.put(commands.size()+1, new Todo(commands.size()+1, input.split(cmd)[1]));
                        break;
                    case "deadline":
                        commands.put(commands.size()+1, new Deadline(commands.size()+1, input.split(cmd)[1]));
                        break;
                    case "event":
                        commands.put(commands.size()+1, new Event(commands.size()+1, input.split(cmd)[1]));
                        break;
                }
                System.out.println(indent+"  "+commands.get(commands.size()));
                System.out.println(indent+"Now you have "+commands.size()+" tasks in the list.");
            }

            System.out.println(line);
            System.out.println();
            input = in.nextLine();
        }

        // Exit Duke
        System.out.println(line);
        System.out.println(indent+"Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
