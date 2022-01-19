import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcome = "Hi! I'm Ruby, How can I help you?";
        printMsg(welcome);
        String command = "";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> textsToDisplay = new ArrayList<>();
        while (true) {
            command = sc.nextLine();
            String[] commandSplitBySpace = command.split(" ");
            if (commandSplitBySpace[0].equals("bye")) {
                break;
            } else if (commandSplitBySpace[0].equals("list")) {
                StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");
                for (int i = 0; i < textsToDisplay.size(); i++) {
                    if (i > 0) {
                        sb.append("\n");
                    }
                    sb.append(Integer.toString(i + 1) + ".[" + textsToDisplay.get(i).getStatusIcon() + "] " + textsToDisplay.get(i).description);
                }
                printMsg(sb.toString());
            } else if (commandSplitBySpace[0].equals("mark")) {
                Task t = textsToDisplay.get(Integer.parseInt(commandSplitBySpace[1]) - 1);
                t.markAsDone();
                printMsg("Good job! I've marked this task as done:\n[" + t.getStatusIcon() + "] " + t.description);
            } else if (commandSplitBySpace[0].equals("unmark")) {
                Task t = textsToDisplay.get(Integer.parseInt(commandSplitBySpace[1]) - 1);
                t.markAsNotDone();
                printMsg("Okay, I've marked this task as not done yet:\n[ ] " + t.description);
            } else {
                textsToDisplay.add(new Task(command));
                printMsg("added: " + command);
            }
        }
        printMsg("Okay, bye! Hope to see you again :)");
    }

    /**
     * Prints the message that is parsed into this method with dividers.
     *
     * @param msg a String containing the message to be printed.
     */
    public static void printMsg(String msg) {
        String divider = "---------------------------------------------------------";
        System.out.println(divider);
        System.out.println(msg);
        System.out.println(divider);
    }
}
