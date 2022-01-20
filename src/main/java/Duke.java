import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "    ______________________________\n";
        System.out.println(line +
                "    Hello! I'm Bob!\n    What can I do for you?\n" + line);
        Scanner sc = new Scanner(System.in);
        String cmd = sc.next();
        while (!cmd.equals("bye")) {
            cmd = sc.next();
            System.out.println(line + "    " + cmd + "\n" + line);
        }
        ;
        System.out.println(line + "    Bye. Hope to see you again soon!\n" + line);
    }
}
