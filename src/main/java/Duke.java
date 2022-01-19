import java.text.Format;
import java.util.Scanner;

public class Duke {

    public static String FormatMsg(String msg) {
        String startFormat = "####################\n";
        String endFormat = "\n####################";
        return startFormat + msg + endFormat;
    }

    public static void DisplayWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String welcomeMsg = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println(FormatMsg(welcomeMsg));
    }

    public static void main(String[] args) {
        DisplayWelcomeMsg();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                String exitMsg = FormatMsg("Bye. Hope to see you again soon!");
                System.out.println(exitMsg);
                return;
            } else {
                System.out.println(FormatMsg(command));
            }
        }
    }
}
