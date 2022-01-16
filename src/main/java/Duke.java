import java.util.Scanner;

public class Duke {
    private static String delimiter = "*******************************************************";

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void echo(String command) {
        System.out.println(command);
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(delimiter);
            System.out.print("Enter your command: ");
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                echo(command);
            }
        }
    }

    public static void main(String[] args) {
        greet();
        run();
        exit();
    }
}