import java.util.Scanner;

@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DM_DEFAULT_ENCODING")
public class Duke {
    public static void main(String[] args) {
        String line = "    ______________________________\n";
        System.out.println(line +
                "    Hello! I'm Bob!\n    What can I do for you?\n" + line);
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            cmd = sc.nextLine();
            System.out.println(line + "    " + cmd + "\n" + line);
        }
        System.out.println(line + "    Bye. Hope to see you again soon!\n" + line);
    }
}
