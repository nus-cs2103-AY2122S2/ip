import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String welcome = "Hi! I'm Ruby, How can I help you?";
        printMsg(welcome);
        String command = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            command = sc.next();
            if (command.equals("bye")) {
                break;
            }
            printMsg(command);
        }
        printMsg("Okay, bye! Hope to see you again :)");
    }

    /**
     * Prints the message that is parsed into this method.
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
