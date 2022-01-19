import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello! I'm Duke\nWhat can i do for you?\n");

        Scanner input = new Scanner(System.in);
        String text = "";

        while (!text.equals("bye")) {
            text = input.next();
            System.out.println("------------------------------------------------------------");
            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(text);
            }
            System.out.println("------------------------------------------------------------");
        }

    }
}
