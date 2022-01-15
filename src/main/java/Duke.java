import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Enkel";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String frame = "_____________________________________________\n";
        System.out.println(frame + "Hello! I\'m " + name + "\nWhat can I do for you?\n" + frame);

        Scanner sc = new Scanner(System.in);
        String command;
        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(frame + "Bye. Hope to see you again soon!\n" + frame);
                break;
            } else {
                System.out.println(frame + command + "\n" + frame);
            }
        }
    }
}
