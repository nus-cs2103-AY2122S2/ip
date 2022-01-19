import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcome = "Hi! I'm Ruby, How can I help you?";
        printMsg(welcome);
        String command = "";
        Scanner sc = new Scanner(System.in);
        ArrayList<String> textsToDisplay = new ArrayList<>();
        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < textsToDisplay.size(); i++) {
                    if (i > 0) {
                        sb.append("\n");
                    }
                    sb.append(Integer.toString(i + 1) + ". " + textsToDisplay.get(i));
                }
                printMsg(sb.toString());
            } else {
                textsToDisplay.add(command);
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
