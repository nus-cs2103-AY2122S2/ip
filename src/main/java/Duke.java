import java.util.Scanner;

public class Duke {
    private static int LINE_BREAK_LENGTH = 60; // length for line break
    private static String LINE_BREAK = "_".repeat(LINE_BREAK_LENGTH); // string for horizontal line break

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = "             ___      .______       __  \n"
                + "            /   \\     |   _  \\     |  | \n"
                + "           /  ^  \\    |  |_)  |    |  | \n"
                + "          /  /_\\  \\   |      /     |  | \n"
                + "         /  _____  \\  |  |\\  \\----.|  | \n"
                + "        /__/     \\__\\ | _| `._____||__| \n"
                + "\n";

        String introMessage = String.format("\t%s\n"
                + "%s\n"
                + "\tDear Master, I'm Ari, your Personal Assistant Chatbot\n"
                + "\tWhat can I do for you?\n"
                + "\t%s", LINE_BREAK, logo, LINE_BREAK);
        System.out.println(introMessage + "\n");

        String cmd = "start"; // starting value that is not "bye" to start the loop
        while (!cmd.equals("bye")) {
            cmd = scanner.nextLine().toLowerCase();
            if (cmd.equals("bye")) {
                printWithTab(LINE_BREAK);
                printWithTab("Have a nice day");
                printWithTab(LINE_BREAK + "\n");
                continue;
            } else {
                command(cmd);
            }
        }
        scanner.close();
    }

    public static void printWithTab(String message) {
        System.out.println("\t" + message);
    }

    public static void command(String command) {
        printWithTab(LINE_BREAK);
        printWithTab(command);
        printWithTab(LINE_BREAK + "\n");
    }
}
