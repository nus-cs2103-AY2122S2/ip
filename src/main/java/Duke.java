import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        printMsg("Hello! I am Spike ⊂( ・ ̫・)⊃\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // Get the command as one line
            String command = sc.nextLine();
            // Also split the command into words for easy processing
            String[] commandWords =  command.split(" ");
            if (commandWords[0].equals("bye")) {
                printMsg("See you soon! ﾍ(=￣∇￣)ﾉ");
                break;
            }
            processCommand(command, commandWords);
        }
        sc.close();
        return;
    }

    /**
     * Process the command entered by user.
     */
    public static void processCommand(String command, String[] commandWords) {
        switch (commandWords[0]) {
            case "list":
                printList();
                break;
            case "mark":
                Task toMark = taskList.get(Integer.parseInt(commandWords[1]) - 1);
                toMark.markAsDone();
                printMsg("Great! One more task done:\n" + toMark);
                break;
            case "unmark":
                Task toUnmark = taskList.get(Integer.parseInt(commandWords[1]) - 1);
                toUnmark.markAsNotDone();
                printMsg("Oops, I've marked this task as not done yet:\n" + toUnmark);
                break;
            default:
                taskList.add(new Task(command));
                printMsg("added: " + command);
        }
    }

    /**
     * Format and print the response to the console.
     */
    public static void printMsg(String msg) {
        System.out.println("-------------------------------------------------\n"
                + msg + "\n"
                + "-------------------------------------------------");
    }

    /**
     * Print all items in the list
     */
    public static void printList() {
        int i = 1;
        String result = "";
        for (Task task : taskList) {
            if (i == taskList.size()) {
                result = result + i + ". " + task;
            } else {
                result = result + i + ". " + task + "\n";
            }
            i++;
        }
        printMsg(result);
    }
}
