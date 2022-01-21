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

        while (true) {
            String command = sc.nextLine().trim();

            if (command.equals("bye")) {
                System.out.println(buildMessage("Bye. Hope to see you again soon!"));
                break;
            } else {
                System.out.println(buildMessage(command));
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
