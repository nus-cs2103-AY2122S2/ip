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
        String[] list = new String[100];
        int tasks = 0;

        while (!text.equals("bye")) {
            text = input.next();
            tasks++;
            System.out.println("------------------------------------------------------------");
            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (text.equals("list")) {
                for (int i = 1; i < tasks; i++) {
                    System.out.println(i + ". " + list[i - 1]);
                }
            } else {
                list[tasks - 1] = text;
                System.out.println("added: " + text);
            }
            System.out.println("------------------------------------------------------------");
        }

    }
}
