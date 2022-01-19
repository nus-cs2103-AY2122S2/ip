import java.lang.*;
import java.util.*;

public class Duke {
    static String indent = "    ";
    static String line = "    ____________________________________________________________";

    public static void main(String[] args) {
        // Greeting
        System.out.println(line);
        System.out.println(indent+"Hello! I'm Duke\n"+
                indent+
                "What can I do for you?");
        System.out.println(line);

        // Data Structures
        Map<Integer, String> commands = new HashMap<Integer, String>();

        Scanner in = new Scanner(System.in);
        String cmd = in.nextLine();
        while (!cmd.equals("bye")) {

            System.out.println(line);
            if (cmd.equals("list")) {
                for (int i=1; i<=commands.size(); i++) {
                    System.out.println(indent+i+". "+commands.get(i));
                }
            } else {
                System.out.println(indent+"added: "+cmd);
                commands.put(commands.size()+1, cmd);
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
