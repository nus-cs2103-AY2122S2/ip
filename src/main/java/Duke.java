import java.util.Scanner;
import java.util.logging.Handler;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm Duke!");
        System.out.println("What can I do for you?\n");

        while (true) {
            Scanner scn = new Scanner(System.in);
            String input = scn.nextLine();
            if (input.equals(Handlers.BYE.label)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
