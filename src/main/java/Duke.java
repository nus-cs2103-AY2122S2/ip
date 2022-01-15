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
            case "todo":
                ToDo newTD = new ToDo(command.substring(command.indexOf("todo") + 5));
                taskList.add(newTD);
                printAddedTask(newTD);
                break;
            case "deadline":
                // Extract description and deadline and pass to constructor
                Deadline newD = new Deadline(command.substring(command.indexOf("deadline") + 9, command.indexOf("/by") - 1)
                    , command.substring(command.indexOf("/by") + 4));
                taskList.add(newD);
                printAddedTask(newD);
                break;
            case "event":
                Deadline newE = new Deadline(command.substring(command.indexOf("event") + 6, command.indexOf("/at") - 1)
                        , command.substring(command.indexOf("/at") + 4));
                taskList.add(newE);
                printAddedTask(newE);
                break;
            default:
                printMsg("Sorry, I am not programmed to do this yet :(");
        }
    }

    /**
     * Format and print general response
     */
    public static void printMsg(String msg) {
        System.out.println("-------------------------------------------------\n"
                + msg + "\n"
                + "-------------------------------------------------");
    }

    /**
     * Format response to request to add task
     */
    public static void printAddedTask(Task task) {
        System.out.println("-------------------------------------------------\n"
                + "Got it. I've added this task:\n"
                + String.format("    %s\n", task.toString())
                + String.format("Now you have %s task(s) in the list.\n", taskList.size())
                + "-------------------------------------------------");
    }

    /**
     * Print all items in the list
     */
    public static void printList() {
        int i = 1;
        String result = "Here are the tasks in your list:\n";
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
