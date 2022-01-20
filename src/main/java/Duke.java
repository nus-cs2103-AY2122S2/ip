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
        List<String> list = new ArrayList<>();

        String cmd = sc.nextLine();

        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                int count = 1;
                System.out.print(line);
                for (String entry : list) {
                    System.out.println("    " + count++ + ". " + entry);
                }
                System.out.println(line);
            } else {
                list.add(cmd);
                System.out.println(line + "    added: " + cmd + "\n" + line);
            }
            cmd = sc.nextLine();
        }
        System.out.println(line + "    Bye. Hope to see you again soon!\n" + line);
    }
}
