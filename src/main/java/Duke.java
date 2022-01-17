import java.util.Scanner;

public class Duke {
    private static final String INDENTATION = "    ";
    private static final String DIVIDER = "____________________________________________________________";
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";


    public static void main(String[] args) throws Exception {
        final Scanner input = new Scanner(System.in);
        System.out.println(Duke.constructResponse(Duke.GREETING));
        try {
            while (true) {
                final String command = input.nextLine();
                if (command.equals("bye")) {
                    System.out.println(Duke.constructResponse(Duke.GOODBYE));
                    break;
                }
                System.out.println(Duke.constructResponse(command));
            }
        } finally {
            input.close();
        }
    }

    private static String constructResponse(String content) {
        final String divider = Duke.INDENTATION + Duke.DIVIDER + "\n";
        final String response =
                Duke.INDENTATION + " " + content.replaceAll("\n", "\n " + Duke.INDENTATION) + "\n";
        return divider + response + divider;
    }
}
