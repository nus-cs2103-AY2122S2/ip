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

        // Data Structures
        Map<Integer, Command> commands = new HashMap<Integer, Command>();

        Scanner in = new Scanner(System.in);
        String cmd = in.nextLine();
        while (!cmd.equals("bye")) {
            System.out.println(line);
            StringTokenizer st = new StringTokenizer(cmd);
            String token1 = st.nextToken();

            if (cmd.equals("list")) {
                for (int i=1; i<=commands.size(); i++) {
                    System.out.println(indent+i+"."+commands.get(i));
                }
            } else if (token1.equals("mark")) {
                int rank = Integer.parseInt(st.nextToken());
                commands.get(rank).setDone(true);
                System.out.println(indent + "Nice! I've marked this task as done:");
                System.out.println(indent + "  " + commands.get(rank));
            } else if (token1.equals("unmark")) {
                int rank = Integer.parseInt(st.nextToken());
                commands.get(rank).setDone(false);
                System.out.println(indent + "OK, I've marked this task as not done yet:");
                System.out.println(indent + "  " + commands.get(rank));
            } else {
                System.out.println(indent+"added: "+cmd);
                commands.put(commands.size()+1, new Command(commands.size()+1, cmd));
            }
            System.out.println(line);

            cmd = in.nextLine();
        }

        // Exit Duke
        System.out.println(line);
        System.out.println(indent+"Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
