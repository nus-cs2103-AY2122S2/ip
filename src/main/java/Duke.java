import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "    ____________________________________________________________";
        System.out.println(logo);
        System.out.println(line + "\n    Hello! I'm Duke by A0221330A.\n    What can I do for you?\n" + line);

        boolean exit = false;
        while (!exit) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "\n    Bye. Hope to see you again soon!\n" + line);
                exit = true;
            } else {
                System.out.println(line + "\n    " + input + "\n" + line);
            }
        }

    }
}
