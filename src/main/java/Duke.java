import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printMsg("Hello! I am Spike ⊂( ・ ̫・)⊃\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) {
                printMsg("See you soon! ﾍ(=￣∇￣)ﾉ");
                sc.close();
                return;
            } else {
                printMsg(command);
            }
        }
    }

    /**
     * Format and print the response to the console.
     */
    public static void printMsg(String msg) {
        System.out.println("-------------------------------------------------\n"
                + msg + "\n"
                + "-------------------------------------------------\n");
    }
}
