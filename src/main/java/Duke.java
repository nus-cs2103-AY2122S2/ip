import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(buildMessage("""
            Hello! I'm Duke
            What can I do for you?""")
            );

        Scanner sc = new Scanner(System.in);

        List<String> pastCommands = new ArrayList<>();

        while (true) {
            String command = sc.nextLine().trim();

            if (command.equals("bye")) {
                System.out.println(buildMessage("Bye. Hope to see you again soon!"));
                break;
            } else if (command.equals("list")) {
                StringBuilder list = new StringBuilder();
                for (int i = 0; i < pastCommands.size(); i++) {
                    list.append(String.format("%d. %s\n", i + 1, pastCommands.get(i)));
                }
                System.out.println(buildMessage(list.toString().trim()));
            } else {
                pastCommands.add(command);
                System.out.println(buildMessage(String.format("added: %s", command)));
            }
        }

        sc.close();
    }

    /** Given a message, returns the formatted message for Duke to display
     * @param message The message to display
     */
    public static String buildMessage(String message) {
        return String.format("""
                        ____________________________________________________________
                        %s
                        ____________________________________________________________""",
                message
        );
    }
}
