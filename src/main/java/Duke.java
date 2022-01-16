import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String dash = "    ____________________________________\n";
        System.out.println(dash + "    Hello! I\'m Duke\n    What can I do for you?\n" + dash);

         while (true) {
             String input = sc.nextLine();
             if (input.equals("bye")) {
                 System.out.println(dash + "    Bye! Hope to see you again soon!\n" + dash);
                 break;
             }
             System.out.println(dash + "    " + input + "\n" + dash);
        }
    }
}
