import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String border = "    ____________________________________________________________\n";
        String spacing = "    ";
        System.out.println("Hello from\n" + logo);
        Scanner myScanner = new Scanner(System.in);
        System.out.println(border + spacing +
                "Hello! I'm Duke\n" + spacing +
                "What can I do for you?\n" +
                border);
        String cmd = myScanner.nextLine();
        while (!cmd.equals("bye")) {
            System.out.println(border + spacing + cmd + "\n" + border);
            cmd = myScanner.nextLine();
        }
        System.out.println(border + spacing + "Bye. Hope to see you again soon!\n" + border);
    }
}
