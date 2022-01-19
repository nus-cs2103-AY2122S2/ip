import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> items = new ArrayList<>();

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
            if (command.equals("list")) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i + 1) + ". " + items.get(i));
                }
            } else {
                items.add(command);
                System.out.println("added: " + command);
            }
            command = sc.nextLine();
        }

        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
