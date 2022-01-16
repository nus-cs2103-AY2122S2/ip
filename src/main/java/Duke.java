import java.util.Scanner;

public class Duke {
    private static int LINE_BREAK_LENGTH = 60; // length for line break
    private static String LINE_BREAK = "_".repeat(LINE_BREAK_LENGTH); // string for horizontal line break

    private static int MAX_SIZE = 100;
    private static int listSize = 0;
    private static String[] toDoList = new String[MAX_SIZE];

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
                printWithTab("Have a nice day Master");
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
        if (command.equals("list")) {
            if (listSize == 0) {
                printWithTab("Dear Master, you have not added anything");
            } else {
                printWithTab("Dear Master, here is a list of things you have added:\n");
                for (int i = 0; i < listSize; i++) {
                    printWithTab(String.format("%d. %s", i + 1, toDoList[i]));
                }
            }
        } else {
            addTask(command);
        }
        printWithTab(LINE_BREAK + "\n");
    }

    public static void addTask(String todo) {
        toDoList[listSize] = todo;
        listSize++;

        printWithTab(String.format("Understood, I have added \"%s\" to the list", todo));
    }
}
