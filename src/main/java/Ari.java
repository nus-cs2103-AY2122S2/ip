import java.util.Arrays;
import java.util.Scanner;

public class Ari {
    private static int LINE_BREAK_LENGTH = 60; // length for line break
    private static String LINE_BREAK = "_".repeat(LINE_BREAK_LENGTH); // string for horizontal line break

    private static int MAX_SIZE = 100;
    private static String filePath = "data/ari.txt";
    
    private static TaskList toDoList = new TaskList(MAX_SIZE, filePath);

    public static void main(String[] args) {
        toDoList.load();
        Scanner scanner = new Scanner(System.in);

        String logo = "             ___      .______       __         \n"
                + "            /   \\     |   _  \\     |  |      \n"
                + "           /  ^  \\    |  |_)  |    |  |      \n"
                + "          /  /_\\  \\   |      /     |  |      \n"
                + "         /  _____  \\  |  |\\  \\----.|  |      \n"
                + "        /__/     \\__\\ | _| `._____||__|      \n"
                + "\n";

        String introMessage = String.format("\t%s\n"
                + "%s\n"
                + "\tDear Master, I am Ari, your Personal Assistant Chatbot\n"
                + "\tWhat can I do for you?\n" + "\t%s", LINE_BREAK, logo, LINE_BREAK);
        System.out.println(introMessage + "\n");

        String cmd = "start"; // starting value that is not "bye" to start the loop
        while (!cmd.equals("bye")) {
            cmd = scanner.nextLine();
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
        String instr = cmdList[0].toLowerCase(); // instruction to execute

        System.out.println("\t" + LINE_BREAK);

        String msg = String.join(" ", Arrays.copyOfRange(cmdList, 1, cmdList.length));
        switch (instr) {
        case "list":
            System.out.println(String.format("%s", toDoList));
            break;
        case "mark":
            toDoList.markTask(msg);
            break;
        case "unmark":
            toDoList.unmarkTask(msg);
            break;
        case "todo":
            toDoList.addTask(instr, msg);
            break;
        case "deadline":
            toDoList.addTask(instr, msg);
            break;
        case "event":
            toDoList.addTask(instr, msg);
            break;
        case "delete":
            toDoList.deleteTask(msg);
            break;
        default:
            System.out.println("\tI am sorry Master, I am afraid I do not know what you mean");
        }

        System.out.println("\t" + LINE_BREAK + "\n");
    }
}
