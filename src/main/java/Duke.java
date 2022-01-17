import java.util.Scanner;

public class Duke {
    String tasks[] = new String[100];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____  _____ ____  ____ __  __\n"
                + "|  _ \\|  _  |  _ \\|  _ \\\\ \\/ /\n"
                + "| |_| | | | | |_| | |_| |\\  /\n"
                + "| |_| | |_| | |_| | |_| ||  |\n"
                + "|____/|_____|____/|____/ |__|\n";
        System.out.println("Hello from\n" + logo);
        String dash = "    ____________________________________\n";
        System.out.println(dash + "    Hello! I\'m Bobby\n    What can I do for you?\n" + dash);

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
