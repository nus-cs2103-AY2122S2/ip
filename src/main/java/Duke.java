import java.util.Scanner;

public class Duke {
    private static int LINE_BREAK_LENGTH = 60; // length for line break
    private static String LINE_BREAK = "_".repeat(LINE_BREAK_LENGTH); // string for horizontal line break

    private static int MAX_SIZE = 100;
    private static TaskList toDoList = new TaskList(MAX_SIZE);

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
                + "\tWhat can I do for you?\n" + "\t%s", LINE_BREAK, logo, LINE_BREAK);
        System.out.println(introMessage + "\n");

        String cmd = "start"; // starting value that is not "bye" to start the loop
        while (!cmd.equals("bye")) {
            cmd = scanner.nextLine().toLowerCase();
            if (cmd.equals("bye")) {
                System.out.println("\t" + LINE_BREAK);
                System.out.println("\tHave a nice day Master");
                System.out.println("\t" + LINE_BREAK + "\n");
                continue;
            } else {
                command(cmd);
            }
        }
        scanner.close();
    }

    public static void command(String message) {
        String[] cmdList = message.split(" ");
        String instr = cmdList[0]; // instruction to execute

        System.out.println("\t" + LINE_BREAK);
        switch (instr) {
        case "list":
            System.out.println(String.format("%s", toDoList));
            break;
        case "mark":
            int markIndex = Integer.valueOf(cmdList[1]);
            toDoList.markTask(markIndex - 1);
            break;
        case "unmark":
            int unmarkIndex = Integer.valueOf(cmdList[1]);
            toDoList.unmarkTask(unmarkIndex - 1);
            break;
        default:
            toDoList.addTask(message);
        }
        System.out.println("\t" + LINE_BREAK + "\n");
    }
}
