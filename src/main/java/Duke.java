import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");

        Scanner sc = new Scanner(System.in);

        System.out.println("What can I do for you?");
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println(command);
            command = sc.nextLine();
        }

        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
