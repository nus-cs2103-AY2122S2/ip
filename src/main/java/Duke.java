import java.lang.*;
import java.util.*;

public class Duke {
    static String indent = "    ";
    static String line = "    ____________________________________________________________";
    public static void main(String[] args) {
        System.out.println(line);
        System.out.println(indent+"Hello! I'm Duke\n"+
                indent+
                "What can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);
        String cmd = in.nextLine();
        while (!cmd.equals("bye")) {
            System.out.println(line);
            System.out.println(indent+cmd);
            System.out.println(line);
            cmd = in.nextLine();
        }

        System.out.println(line);
        System.out.println(indent+"Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
